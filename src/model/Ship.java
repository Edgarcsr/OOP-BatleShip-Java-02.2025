package model;
import enums.Orientation;
import enums.ShipType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Ship {
    private final ShipType type;
    private Orientation orientation;
    private Set<Coordinate> coordinates = new HashSet<>();
    private final Set<Coordinate> hitsCoordinates = new HashSet<>();


    public Ship(ShipType type, Orientation orientation) {
        this.type = type;
        this.orientation = orientation;
    }

    public Set<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinates.add(coordinate);
    }
    public boolean registryHitAt(Coordinate coords) {
        return coordinates.contains(coords) && hitsCoordinates.add(coords);
    }

    public boolean isSunk() {
        return coordinates.size() == hitsCoordinates.size();
    }


    public ShipType getType() {
        return type;
    }
    public Orientation getOrientation() { return orientation;}
}

