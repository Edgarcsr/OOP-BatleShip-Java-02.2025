package  model;

import enums.CellStatus;
import enums.Orientation;
import utils.RandomCellGenerator;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Cell[][] grid;
    private List<Ship> ships;
    private final RandomCellGenerator randomCellGenerator;
    private List<String> missedShoots = new ArrayList<>();
    private List<String> successfullShots = new ArrayList<>();
    private List<String> destructiveShoots = new ArrayList<>();
    private String unespectedResult = "No se ha procesado el disparo correctamente";


    public Board() {
        grid = new Cell[10][10];
        for ( int i = 0; i < grid.length; i++ ) {
            for( int j = 0; j < grid[i].length; j++ ) {
                grid[i][j] = new Cell( new Coordinate(i, j) );
            }
        }
        randomCellGenerator = new RandomCellGenerator(this);
    }

    public Board( int row, int column, List<Ship> ships ) {

        grid = new Cell[row][column];

        for ( int i = 0; i < grid.length; i++ ) {
            for( int j = 0; j < grid[i].length; j++ ) {
                grid[i][j] = new Cell( new Coordinate(i, j) );
            }
        }
        this.ships = ships;
        randomCellGenerator = new RandomCellGenerator(this);
    }

    public void placeShips() {
        int numberOfShips = ships.size() - 1;

        while(numberOfShips > 0) {

            Cell randomCell = randomCellGenerator.getCell();
            Ship boat = ships.get(numberOfShips);

            if(validatePlacement(boat, randomCell)) {
                placeBoat(boat, randomCell);
                numberOfShips--;
            }
        }
    }

    private void placeBoat( Ship boat, Cell place ) {

        int startRow = place.getCoordinate().getRow();
        int startColumn = place.getCoordinate().getColumn();

        for( int i = 0; i < boat.getType().getSize(); i++ ) {

            if ( boat.getOrientation() == Orientation.HORIZONTAL ) {
                grid[startRow][startColumn + i].setShip(boat);

            } else if ( boat.getOrientation() == Orientation.VERTICAL ) {
                grid[startRow + i][startColumn].setShip(boat);

            }
            boat.setCoordinate(place.getCoordinate());
        }
    }

    private boolean validatePlacement(Ship ship, Cell placeInit ) {

        int size = ship.getType().getSize();
        int startRow = placeInit.getCoordinate().getRow();
        int startCol = placeInit.getCoordinate().getColumn();

        if (ship.getOrientation() == Orientation.HORIZONTAL) {
            // Verifica límites
            if (!keepBoundaries(placeInit, size)) return false;
            // Verifica espacio para el barco y dos celdas a cada lado
            for (int i = -2; i < size + 2; i++) {
                int col = startCol + i;
                if (col >= 0 && col < grid[0].length) {
                    if (grid[startRow][col].hasShip()) return false;
                }
            }
            // Verifica dos filas arriba y abajo del barco
            for (int offset = -2; offset <= 2; offset++) {
                int col = startCol + offset;
                if (col >= 0 && col < grid[0].length) {
                    for (int rowDelta = -2; rowDelta <= 2; rowDelta++) {
                        int row = startRow + rowDelta;
                        if (row >= 0 && row < grid.length && rowDelta != 0) {
                            if (grid[row][col].hasShip()) return false;
                        }
                    }
                }
            }
        } else { // VERTICAL
            if (!keepBoundaries(placeInit, size)) return false;
            for (int i = -2; i < size + 2; i++) {
                int row = startRow + i;
                if (row >= 0 && row < grid.length) {
                    if (grid[row][startCol].hasShip()) return false;
                }
            }
            for (int offset = -2; offset <= 2; offset++) {
                int row = startRow + offset;
                if (row >= 0 && row < grid.length) {
                    for (int colDelta = -2; colDelta <= 2; colDelta++) {
                        int col = startCol + colDelta;
                        if (col >= 0 && col < grid[0].length && colDelta != 0) {
                            if (grid[row][col].hasShip()) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean keepBoundaries(Cell cellProposal, int size ) {

        int init = cellProposal.getCoordinate().getRow();
        return  init + size  <=  grid.length - init;
    }

    public int getSize() {
        return grid.length;
    }

    public Cell getCell(int row, int column) {
        return grid[row][column];
    }

    public void registryShot( Coordinate coordinate ) {
        Cell cell = grid[coordinate.getRow()][coordinate.getColumn()];
        if( cell.getCellStatus() == CellStatus.MISS || cell.getCellStatus() == CellStatus.HIT ) {
            getResultShoot("Ya has disparado a esa celda, intentalo otra vez");
            return;
        }

        cell.processHit(coordinate);

        switch (cell.getCellStatus()) {
            case MISS:
                missedShoots.add("fallado en: " + coordinate.getRow() + "," + coordinate.getColumn());
                getResultShoot(missedShoots.getLast());
                break;
            case HIT:
                successfullShots.add("acertado en: " + coordinate.getRow() + "," + coordinate.getColumn());
                getResultShoot(successfullShots.getLast());
                if (cell.getShip().isSunk()) {
                    destructiveShoots.add("hundido un: " + cell.getShip().getType());
                    getResultShoot(destructiveShoots.getLast());
                }
                break;
            default:
                System.out.println(unespectedResult + " Reinicia el juego ");
        }
    }

    private void getResultShoot(String result) {
        System.out.println("Tu disparo ha " + result);
    }

    public void printPerformanceRank() {
        int totalShoots = missedShoots.size() + successfullShots.size();
        if (totalShoots == 0) {
            System.out.println("No se han realizado disparos.");
            return;
        }
        double failurePercentage = (double) missedShoots.size() / totalShoots * 100;
        double correctPercentage = (double) successfullShots.size() / totalShoots * 100;
        double destructivePercentage = (double) destructiveShoots.size() / totalShoots * 100;

        String rango;
        if (failurePercentage >= 70) {
            rango = "penoso";
        } else if (failurePercentage >= 50) {
            rango = "chulapo";
        } else if (failurePercentage >= 30) {
            rango = "destructor";
        } else {
            rango = "rambo";
        }
        System.out.printf("De %d disparos, has acertado %.2f%%, de los cuales %.2f%% han sido destructivos -> Eres %s%n", totalShoots, correctPercentage, destructivePercentage, rango);
        System.out.printf("Has fallado %.2f%% de los disparos -> Eres %s%n", failurePercentage, rango);
    }

    public void printBoard(boolean reveal) {
        int rows = grid.length;
        int columns = grid[0].length;

        System.out.print("   ");
        for (int i = 0; i < columns; i++) {
            System.out.printf("  %d ", i);
        }
        System.out.println();
        System.out.print("   ");
        for (int i = 0; i < columns; i++) {
            System.out.print("+---");
        }
        System.out.println("+");
        for (int i = 0; i < rows; i++) {
            System.out.printf(" %c ", 65 + i); // Letra de la fila
            for (int j = 0; j < columns; j++) {
                System.out.printf("| %s ", grid[i][j].display(reveal));
            }
            System.out.println("|");
            System.out.print("   ");
            for (int k = 0; k < columns; k++) {
                System.out.print("+---");
            }
            System.out.println("+");
        }
        System.out.println();
    }
}
