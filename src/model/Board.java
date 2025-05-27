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

public class Board {
    private static Cell[][] grid;
    private List<Ship> ships;


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
    public Board( int row, int column ) {

        grid = new Cell[row][column];

        for ( int i = 0; i < grid.length; i++ ) {
            for( int j = 0; j < grid[i].length; j++ ) {
                grid[i][j] = new Cell( new Coordinate(i, j) );
            }
        }
    }

    private void placeShip() {
        if(canPlaceIt( )) {

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

        }
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

}
