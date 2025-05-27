package  model;

/*
* #### **6. `Board`**
- **Propósito**: Representa el tablero del jugador, compuesto por una matriz de celdas y una lista de barcos.
- **Relaciones**:
  - Contiene una matriz de `Cell` y una lista de `Ship`.
  - Utiliza `Coordinate` para identificar posiciones.
- **Flujo**:
  - Gestiona la colocación de barcos en celdas específicas.
  - Procesa disparos al identificar la celda correspondiente y delegar la acción.
  - Verifica si todos los barcos han sido hundidos.*/
import enums.Orientation;
import model.Ship;
import enums.ShipType;
import java.util.List;
import utils.RandomCellGenerator;

public class Board {
    private static Cell[][] grid;
    private List<Ship> ships;
    private final RandomCellGenerator randomCellGenerator = new RandomCellGenerator(this);


    public Board() {
        grid = new Cell[10][10];
        for ( int i = 0; i < grid.length; i++ ) {
            for( int j = 0; j < grid[i].length; j++ ) {
                grid[i][j] = new Cell( new Coordinate(i, j) );
            }
        }
    }
    /*
    * Buque (B):  horizontales consecutivas del tablero.
     Acorazado (Z):  horizontales consecutivas del
    tablero.
     Portaaviones (P): verticales c*/
    public Board( int row, int column, List<Ship> ships ) {

        grid = new Cell[row][column];

        for ( int i = 0; i < grid.length; i++ ) {
            for( int j = 0; j < grid[i].length; j++ ) {
                grid[i][j] = new Cell( new Coordinate(i, j) );
            }
        }

        this.ships = ships;
    }
//char kindOfShip, int size, int quantity, int position){
    public void placeShips( ) {
        int numberOfShips = ships.size();
        while(numberOfShips > 0) {
            Cell randomCell = randomCellGenerator.getCell();
            // Ojo, aquí comenzamos a colocar los barcos de toda la lista uno a uno independiente del tipo que sea
            // No sé si debo validar que no sobreescriba una barco ya colocado que eso no lo contemplé


            for(Ship boat: ships) {
                if()
            }

        }
        /* int numberOfShips = quantity;
        while (numberOfShips > 0) {
            int row = randomize(0, rows - 1);
            int col = randomize(0, columns - 1);
            //System.out.println("row: "+row+" col: "+col);
            //System.out.println("number of ships: "+numberOfShips);
            if(validateBox(size,position, row, col)) {
            //System.out.println("Casilla validada, se coloca en"+"row: "+row+" col: "+col);
            // position 0 = horizontal, 1 = vertical
                if (position == 0) {
                    //System.out.println("Colocando barco en horizontal"+"Tipo de barco: "+kindOfShip+" Tamaño: "+size);
                    for (int i = 0; i < size; i++) {
                        computerBoard[row][col + i] = kindOfShip;
                    }
                } else {
                    //System.out.println("Colocando barco en vertical"+"Tipo de barco: "+kindOfShip+" Tamaño: "+size);
                    for (int i = 0; i < size; i++) {
                        computerBoard[row + i][col] = kindOfShip;
                    }
                }
                numberOfShips--;
            }
        } */
        for ( Ship ship : ships ) {
            Cell place = ship.getPlace();
            placeShip(ship, place);
        }
    }

    private void placeBoat( Ship ship, Cell place ) {

        if(canPlaceIt(ship, place)) {
            int startRow = place.getCoordinate().getRow();
            int startColumn = place.getCoordinate().getColumn();

            for( int i = 0; i < ship.getType().getSize(); i++ ) {
                if ( ship.getOrientation() == Orientation.HORIZONTAL ) {
                    grid[startRow][startColumn + i].setShip(ship);
                } else if ( ship.getOrientation() == Orientation.VERTICAL ) {
                    grid[startRow + i][startColumn].setShip(ship);Ship(ship);
                }
            } 
        }
    }

    private boolean canPlaceIt( Ship ship, Cell placeInit ) {
        int safeDistance = ship.getType().getSize()+2;
        int startRow = placeInit.getCoordinate().getRow();
        int startColumn = placeInit.getCoordinate().getColumn();

        if ( ship.getOrientation() == Orientation.HORIZONTAL ) {
           //Primero compruebo que del inicio al final no salga del tablero
           if(safeLimits(placeInit, ship.getType().getSize())){
                //Recorro las celdas que ocupará el barco y compruebo que no haya ningún barco
               for( int i = startRow; i <= safeDistance; i++ ) {
                   Cell cell = grid[startRow][i];
                     if ( cell.hasShip() ) {
                          return false; // Si hay un barco en la celda, no se puede colocar
                    }
               }
           }
        } else if ( ship.getOrientation() == Orientation.VERTICAL ) {
            if(safeLimits(placeInit, ship.getType().getSize())) {
                //Recorro las celdas que ocupará el barco y compruebo que no haya ningún barco
                for( int i = startColumn; i <= safeDistance; i++ ) {
                    Cell cell = grid[i][startColumn];
                    if ( cell.hasShip() ) {
                        return false; // Si hay un barco en la celda, no se puede colocar
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

    public void processShot( Coordinate coordinate ) {

        int row = coordinate.getRow();
        int column = coordinate.getColumn();

        if ( row >= 0 && row < grid.length && column >= 0 && column < grid[row].length ) {
            Cell cell = grid[row][column];
            cell.processHit();
        }
    }


}
