package model;

import enums.CellStatus;
import java.util.Scanner;

import static utils.CoordinatesValidator.*;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, Board board, int attempts) {
        super(name, board, attempts);
    }

    @Override
    public void nextShot(Board enemyBoard) {
        System.out.println("Acordar!! "+ name + ", é a sua vez !");

        if (attempts > 0) {
            Coordinate coordinatesChosen = getShotCoordinate();

            if (attempts == 4) System.out.println("Você só tem mais algumas tentativas, se apresse!");
            else if (attempts == 1) System.out.println("Última Tentativa");

          if (!suggestGotFailShot(coordinatesChosen)) {
              System.out.printf("%s vejo pouco sucesso em sua decisão.\n", name);
          }

            enemyBoard.registryShot(coordinatesChosen);
            attempts--;
            checkAttempts();
        } else {
            System.out.println("Acabou suas tentativas, mais sorte da próxima vez!");
        }
    }


    public Coordinate getShotCoordinate() {
        int row, column;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira as coordenadas do disparo (fila e coluna):");
        row = checkLetter(scanner, "Insira a fila (A-J): ");
        column = checkInt(scanner, "Insira a coluna (0-9): ");

        return this.board.getCell(row, column).getCoordinate();
    }

    private boolean suggestGotFailShot(Coordinate coordinate) {
        Cell chosenCell = this.board.getCell(coordinate.getRow(), coordinate.getColumn());
        return chosenCell.getCellStatus() == CellStatus.EMPTY || chosenCell.getCellStatus() == CellStatus.MISS || chosenCell.getCellStatus() == CellStatus.HIT;
    }
}
