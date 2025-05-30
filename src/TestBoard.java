
import model.Board;
import model.Ship;
import enums.ShipType;
import enums.Orientation;

import java.util.ArrayList;
import java.util.List;

public class TestBoard {
    public static void main(String[] args) {
        List<Ship> ships = new ArrayList<>();
        // 5 lanchas
        for (int i = 0; i < 5; i++) {
            ships.add(new Ship(ShipType.LANCHA, Orientation.HORIZONTAL));
        }
        // 3 buques
        for (int i = 0; i < 3; i++) {
            ships.add(new Ship(ShipType.BUQUE, Orientation.HORIZONTAL));
        }
        // 1 acorazado
        ships.add(new Ship(ShipType.ACORAZADO, Orientation.HORIZONTAL));
        // 1 portaaviones
        ships.add(new Ship(ShipType.PORTAVIONES, Orientation.HORIZONTAL));

        Board board = new Board(10, 10, ships);
        board.placeShips();
        board.printBoard(true); // Mostrar barcos
    }
}

