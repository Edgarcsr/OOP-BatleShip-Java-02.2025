package model;



import enums.CellStatus;
import java.util.Scanner;
import static utils.CoordinatesValidator.checkColumn;
import static utils.CoordinatesValidator.checkRow;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, Board board, int attempts) {
        super(name, board, attempts);
    }

    @Override
    public void nextShot(Board enemyBoard) {
        System.out.println("Espabila!! "+ name + ", es tu turno !");
        //Comprueba que me quedan intentos

        if(attempts > 0) {
            Coordinate coordinatesChosen = getShotCoordinate();

            if (suggestGotFailShot(coordinatesChosen)) {
                System.out.printf("%s veo poco éxito en tu decisión.\n", name);
            } else {
                enemyBoard.registryShot(getShotCoordinate());
                attempts--;
                checkAttempts();
            }
        }else {
            if(attempts == 4) System.out.println("Te quedan pocos intentos, ¡aprieta!");
            else if(attempts == 1) System.out.println("¡Último intento!");
            System.out.println("Has agotado tus intentos, ¡mejor suerte la próxima vez!");
        }

    }

    private Coordinate getShotCoordinate() {
        int row, column;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce las coordenadas del disparo (fila y columna):");
        System.out.print("Fila (0-9): ");
        row = checkRow(scanner);

        System.out.println("Columna (A-J): ");
        column = checkColumn(scanner);

        return this.board.getCell(row, column).getCoordinate();
    }

    private boolean suggestGotFailShot(Coordinate coordinate) {
        Cell chosenCell = this.board.getCell(coordinate.getRow(), coordinate.getColumn());

        return chosenCell.getCellStatus() == CellStatus.EMPTY || chosenCell.getCellStatus() == CellStatus.MISS || chosenCell.getCellStatus() == CellStatus.HIT;
    }

}
