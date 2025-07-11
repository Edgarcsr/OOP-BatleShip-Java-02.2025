package utils;

import enums.Difficulty;
import enums.Orientation;
import enums.ShipType;
import model.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoatsGenerator {

    public static List<Ship> generateBoatsList(Difficulty difficulty ) {
        List<ShipType> kindsOfBoats = new ArrayList<>(Arrays.asList(ShipType.values()));

        List<Ship> boats = new ArrayList<>(difficulty.getTotalNumberOfBoats());

        kindsOfBoats.forEach(boat -> {
           for( int i = 0; i < difficulty.getQuantityOfBoats(boat); i++ ) {
                boats.add(new Ship(boat, (boat == ShipType.PORTAVIONES ? Orientation.VERTICAL : Orientation.HORIZONTAL)));
           }
        });

        return boats;
    }
}
