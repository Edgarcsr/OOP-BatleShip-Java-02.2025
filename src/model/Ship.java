package model;
import enums.Orientation;
import enums.ShipType;
import java.util.HashSet;
import java.util.Set;

public class Ship {
    private final ShipType type;
    private final Orientation orientation;
    private final Set<Coordinate> coordinates;
    private final Set<Coordinate> hitsCoordinates;

    public Ship(ShipType type, Orientation orientation) {
        this.type = type;
        this.orientation = orientation;
        this.coordinates = new HashSet<>();
        this.hitsCoordinates = new HashSet<>();
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinates.add(coordinate);
    }

    public void registryHitAt(Coordinate coords) {
        hitsCoordinates.add(coords);
    }

    public boolean isSunk() {
        return coordinates.size() == hitsCoordinates.size();
    }

    public ShipType getType() {
        return type;
    }

    public Orientation getOrientation() { return orientation;}

    public void reset() {
        hitsCoordinates.clear();
    }
}

