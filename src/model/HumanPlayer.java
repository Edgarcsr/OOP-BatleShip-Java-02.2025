package model;

import enums.CellStatus;
import java.util.List;
import java.util.Scanner;

import static utils.CoordinatesValidator.*;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, Board board, int attempts) {
        super(name, board, attempts);
    }

    @Override
    public void nextShot(Board enemyBoard) {
        System.out.println("⚡ Alle mann an Deck!! " + name + ", é a sua vez de atacar! ⚡");

        if (attempts > 0) {
            // Mostrar navios disponíveis para escolha
            Ship selectedShip = selectShip();
            
            if (selectedShip != null) {
                nextShot(enemyBoard, selectedShip);
            }
        } else {
            System.out.println("Acabou suas tentativas, mais sorte da próxima vez!");
        }
    }

    @Override
    public void nextShot(Board enemyBoard, Ship selectedShip) {
        if (attempts > 0) {
            int shotCount = selectedShip.getShotCount();
            
            System.out.printf("⚓ Você selecionou o %s que pode disparar %d torpedo(s)! ⚓\n", 
                            selectedShip.getType().name(), shotCount);
            
            for (int i = 0; i < shotCount && attempts > 0; i++) {
                if (shotCount > 1) {
                    System.out.printf("🎯 Torpedo %d de %d:\n", i + 1, shotCount);
                }
                
                Coordinate coordinatesChosen = getShotCoordinate();

                if (attempts == 4) System.out.println("Você só tem mais algumas tentativas, se apresse!");
                else if (attempts == 1) System.out.println("Última Tentativa");

                if (!suggestGotFailShot(coordinatesChosen)) {
                    System.out.printf("%s vejo pouco sucesso em sua decisão.\n", name);
                }

                enemyBoard.registryShot(coordinatesChosen);
                attempts--;
                checkAttempts();
                
                if (attempts <= 0) break;
            }
        } else {
            System.out.println("Acabou suas tentativas, mais sorte da próxima vez!");
        }
    }

    private Ship selectShip() {
        Scanner scanner = new Scanner(System.in);
        List<Ship> availableShips = board.getShips().stream()
                .filter(ship -> !ship.isSunk())
                .toList();

        if (availableShips.isEmpty()) {
            System.out.println("Nenhum navio disponível!");
            return null;
        }

        System.out.println("🚢 Escolha um navio da sua frota para atacar:");
        for (int i = 0; i < availableShips.size(); i++) {
            Ship ship = availableShips.get(i);
            String specialPower = ship.hasSpecialPower() ? " ⚡(PODER ESPECIAL - 3 TORPEDOS!)⚡" : "";
            System.out.printf("%d. %s (Tamanho: %d)%s\n", 
                            i + 1, ship.getType().name(), ship.getType().getSize(), specialPower);
        }

        int choice;
        do {
            System.out.print("Digite o número do navio (1-" + availableShips.size() + "): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= availableShips.size()) {
                    return availableShips.get(choice - 1);
                } else {
                    System.out.println("Escolha inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Por favor, digite um número válido.");
                scanner.nextLine(); // Limpar buffer
                choice = 0;
            }
        } while (true);
    }


    public Coordinate getShotCoordinate() {
        int row, column;
        Scanner scanner = new Scanner(System.in);

        System.out.println("🎯 Insira as coordenadas do torpedo (fila e coluna):");
        row = checkLetter(scanner, "⚓ Insira a fila (A-J): ");
        column = checkInt(scanner, "🌊 Insira a coluna (0-9): ");

        return this.board.getCell(row, column).getCoordinate();
    }

    private boolean suggestGotFailShot(Coordinate coordinate) {
        Cell chosenCell = this.board.getCell(coordinate.getRow(), coordinate.getColumn());
        return chosenCell.getCellStatus() == CellStatus.EMPTY || chosenCell.getCellStatus() == CellStatus.MISS || chosenCell.getCellStatus() == CellStatus.HIT;
    }
}
