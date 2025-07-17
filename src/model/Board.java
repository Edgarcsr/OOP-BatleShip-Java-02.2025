package  model;

import enums.CellStatus;
import enums.Difficulty;
import enums.Orientation;
import utils.RandomCellGenerator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Board {
    private final Cell[][] grid;
    private final List<Ship> ships;
    private final RandomCellGenerator randomCellGenerator;
    final private List<String> missedShoots = new ArrayList<>();
    final private List<String> successfullyShots = new ArrayList<>();
    final private LinkedHashMap<String, Boolean> destructiveShoots = new LinkedHashMap<>();

    public Board(List<Ship> ships) {
        int defaultSizeOfBoard = Difficulty.EASY.getBoardSize();
        this.grid = new Cell[defaultSizeOfBoard][defaultSizeOfBoard];
        for ( int i = 0; i < grid.length; i++ ) {
            for( int j = 0; j < grid[i].length; j++ ) {
                grid[i][j] = new Cell( new Coordinate(i, j) );
            }
        }
        this.ships = ships;
        this.randomCellGenerator = new RandomCellGenerator(this);
    }

    public Board( int row, int column, List<Ship> ships ) {

        this.grid = new Cell[row][column];

        for ( int i = 0; i < grid.length; i++ ) {
            for( int j = 0; j < grid[i].length; j++ ) {
                grid[i][j] = new Cell( new Coordinate(i, j) );
            }
        }
        this.ships = ships;
        this.randomCellGenerator = new RandomCellGenerator(this);
    }

    public void placeShips() {
        int numberOfShips = ships.size();
        Cell randomCell;
        Ship boat;

        while(numberOfShips > 0) {
            randomCell = randomCellGenerator.getCell();
            boat = ships.get(numberOfShips -1);

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
            boat.setCoordinate(new Coordinate(startRow + i, startColumn + i));
        }
    }

    private boolean validatePlacement(Ship ship, Cell placeInit ) {

        int size = ship.getType().getSize();
        int startRow = placeInit.getCoordinate().getRow();
        int startCol = placeInit.getCoordinate().getColumn();
        int rowLength = grid.length;
        int colLength = grid[0].length;

        if (ship.getOrientation() == Orientation.HORIZONTAL) {
            if (keepBoundaries(placeInit, size, ship.getOrientation())) return false;

            for(int i = 0 ; i < size + 2; i++) {
                if (startCol + i < colLength && grid[startRow][startCol + i].hasShip()) return false;
            }
            for (int i = 1; i <= 2; i++) {
                if (startCol - i >= 0 && grid[startRow][startCol - i].hasShip()) return false;
            }
            if(startRow < grid.length - 1){
                for(int i = 1; i <= 2; i++){
                    if (startRow + i < rowLength  && grid[startRow+i][startCol].hasShip()) return false;
                }
            }
            if(startRow > 0){
                for(int i = 1; i <= 2; i++){
                    if (startRow - i >= 0  && grid[startRow-i][startCol].hasShip() ) return false;
                }
            }
        } else {
            if (keepBoundaries(placeInit, size, ship.getOrientation())) return false;

            for(int i = 0 ; i < size + 2 ; i++){
                if(startRow + i < colLength && grid[startRow + i][startCol].hasShip()) return false;
            }
            for(int i = 1; i <= 2; i++){
                if(startRow - i >= 0 && grid[startRow-i][startCol].hasShip()) return false;
            }
            if(startCol < colLength-1){
                for(int i = 1; i <= 2; i++){
                    if(startCol + i < colLength && grid[startRow][startCol+i].hasShip()) return false;
                }
            }
            if(startCol > 0){
                for(int i = 1; i <= 2; i++){
                    if(startCol - i >= 0 && grid[startRow][startCol-i].hasShip()) return false;
                }
            }
        }
        return true;
    }

    private boolean keepBoundaries(Cell cellProposal, int size, Orientation orientation ) {
        int row = cellProposal.getCoordinate().getRow();
        int col = cellProposal.getCoordinate().getColumn();

        if (orientation == Orientation.HORIZONTAL) {
            return col + size > grid[0].length;
        } else {
            return row + size > grid.length;
        }
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
            getResultShoot("ha fallado otra vez! Ya habías disparado a esta celda. No desperdicies disparos!!");
            return;
        }

        cell.processHit(coordinate);

        String unexpectedResult = "No se ha procesado el disparo correctamente";

        switch (cell.getCellStatus()) {
            //String.valueOf(row + 65);
            case MISS:
                missedShoots.add("fallado en: ".concat((char) (coordinate.getRow() + 65) + "," + coordinate.getColumn()));
                getResultShoot(missedShoots.getLast());
                break;
            case HIT:
                successfullyShots.add("acertado en: " + ((char) (coordinate.getRow() + 65) + "," + coordinate.getColumn()));
                getResultShoot(successfullyShots.getLast());
                if (cell.getShip().isSunk()) {
                    destructiveShoots.put("hundido un: " + cell.getShip().getType(), isAllShipsSunk());
                    getResultShoot(destructiveShoots.keySet().toArray()[destructiveShoots.size() -1 ].toString());
                }
                break;
            default:
                System.out.println(unexpectedResult + " Reinicia el juego ");
        }
    }

    private void getResultShoot(String result) {
        System.out.println("Tu disparo ha " + result);
    }

    public boolean getLastDestructiveShoot() {
        System.out.printf("El último disparo destructivo fue: %s%n", destructiveShoots);
        return destructiveShoots.values().toArray()[destructiveShoots.size() - 1].equals(true);
    }

    public void printPerformanceRank() {
        int totalShoots = missedShoots.size() + successfullyShots.size();
        if (totalShoots == 0) {
            System.out.println("No se han realizado disparos.");
            return;
        }
        double failurePercentage = (double) missedShoots.size() / totalShoots * 100;
        double correctPercentage = (double) successfullyShots.size() / totalShoots * 100;
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

    public boolean isAllShipsSunk() {
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell.hasShip() && !cell.getShip().isSunk()) {
                    return false;
                }
            }
        }
        return true;
    }

    public LinkedHashMap<String,Boolean> getDestructiveShoots() {
        return this.destructiveShoots;
    }

    public void resetBoard() {
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                cell.reset();
            }
        }
        ships.forEach(Ship::reset);
        missedShoots.clear();
        successfullyShots.clear();
        destructiveShoots.clear();
    }
}
