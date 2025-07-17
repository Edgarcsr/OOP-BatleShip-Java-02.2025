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

        List<Ship> boats = new ArrayList<>(difficulty.getTotalQuantityBoats());

        kindsOfBoats.forEach(boat -> {
           for(int i = 0; i < difficulty.getQuantityKindOfBoat(boat); i++ ) {
                boats.add(new Ship(boat, (boat == ShipType.AIRCRAFTCARRIER ? Orientation.VERTICAL : Orientation.HORIZONTAL)));
           }
        });

        return boats;
    }
}
