package  model;

import enums.Orientation;
import utils.RandomCellGenerator;

import java.util.List;

public class Board {
    private final Cell[][] grid;
    private List<Ship> ships;
    private final RandomCellGenerator randomCellGenerator = new RandomCellGenerator(this);
    private String resultShoot;


    public Board() {
        grid = new Cell[10][10];
        for ( int i = 0; i < grid.length; i++ ) {
            for( int j = 0; j < grid[i].length; j++ ) {
                grid[i][j] = new Cell( new Coordinate(i, j) );
            }
        }
    }

    public Board( int row, int column, List<Ship> ships ) {

        grid = new Cell[row][column];

        for ( int i = 0; i < grid.length; i++ ) {
            for( int j = 0; j < grid[i].length; j++ ) {
                grid[i][j] = new Cell( new Coordinate(i, j) );
            }
        }
        this.ships = ships;
    }

    public void placeShips() {
        int numberOfShips = ships.size() - 1;

        while(numberOfShips > 0) {

            Cell randomCell = randomCellGenerator.getCell();
            Ship boat = ships.get(numberOfShips);

            if(canPlaceIt(boat, randomCell)) {
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
        }
    }

    private boolean canPlaceIt( Ship ship, Cell placeInit ) {

        int safeDistance = ship.getType().getSize()+2;
        int startRow = placeInit.getCoordinate().getRow();
        int startColumn = placeInit.getCoordinate().getColumn();

        if ( ship.getOrientation() == Orientation.HORIZONTAL ) {

           if(safeLimits(placeInit, ship.getType().getSize())){
               for( int i = startRow; i <= safeDistance; i++ ) {
                   Cell cell = grid[startRow][i];
                     if ( cell.hasShip() ) {
                          return false;
                    }
               }
           }
        } else if ( ship.getOrientation() == Orientation.VERTICAL ) {

            if(safeLimits(placeInit, ship.getType().getSize())) {
                for( int i = startColumn; i <= safeDistance; i++ ) {
                    Cell cell = grid[i][startColumn];
                    if ( cell.hasShip() ) {
                        return false;
                    }
                }
            }
        }
        return true; // Si no hay barcos en las celdas, se puede colocar, respeta los límites del tablero y el espacio entre barcos
    }

    private boolean safeLimits ( Cell cellProposal, int size ) {

        int init = cellProposal.getCoordinate().getRow();
        return  init + size  <=  grid.length - init;
    }

    public int getSize() {
        return grid.length;
    }

    public Cell getCell(int row, int column) {
        return grid[row][column];
    }
    /*
     public void processHit() {

        if(status != CellStatus.EMPTY) {
            ship.registryHitAt(coordinate);
            status = CellStatus.HIT;
        } else {
            status = CellStatus.MISS;
        }
    }
    * */
    public void registryShot( Coordinate coordinate ) {
        Cell cell = grid[coordinate.getRow()][coordinate.getColumn()];
        cell.processHit(coordinate);
        //Hay que procesar también el disparo para que informe si:  ha sido un acierto, un fallo y si con el disparo se ha hundido un barco.
        if(cell.isMiss()) {

        }
    }

    private String processR
}
