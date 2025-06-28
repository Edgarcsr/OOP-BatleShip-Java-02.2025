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

        if (attempts > 0) {
            Coordinate coordinatesChosen = getShotCoordinate();

            if (attempts == 4) System.out.println("Te quedan pocos intentos, ¡aprieta!");
            else if (attempts == 1) System.out.println("¡Último intento!");

          if (suggestGotFailShot(coordinatesChosen)) {
              System.out.printf("%s veo poco éxito en tu decisión.\n", name);
          }

            enemyBoard.registryShot(coordinatesChosen);
            attempts--;
            checkAttempts();

        } else {
            System.out.println("Has agotado tus intentos, ¡mejor suerte la próxima vez!");
        }
    }


    public Coordinate getShotCoordinate() {
        int row, column;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce las coordenadas del disparo (columna y fila):");
        column = checkColumn(scanner, "Introduce la columna (A-J): ");

        row = checkRow(scanner, "Introduce la fila (0-9): ");

        return this.board.getCell(column, row).getCoordinate();
    }

    private boolean suggestGotFailShot(Coordinate coordinate) {
        Cell chosenCell = this.board.getCell(coordinate.getRow(), coordinate.getColumn());

        return chosenCell.getCellStatus() == CellStatus.EMPTY || chosenCell.getCellStatus() == CellStatus.MISS || chosenCell.getCellStatus() == CellStatus.HIT;
    }

}
