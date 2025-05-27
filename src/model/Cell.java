package model;

import enums.CellStatus;
import enums.ShipType;



public class Cell {

    private Ship ship;
    private  CellStatus status;
    private final Coordinate coordinate;

    public Cell( Coordinate coordinates ) {

        this.ship = null;
        this.status = CellStatus.EMPTY;
        this.coordinate = coordinates;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setShip(Ship ship) {

        this.ship = ship;
        this.status = CellStatus.SHIP;
    }

    public void processHit() {

        if(status != CellStatus.EMPTY) {
            ship.registryHitAt(coordinate);
            status = CellStatus.HIT;
        } else {
            status = CellStatus.MISS;
        }
    }

    public boolean hasShip() {
        return ship != null;
    }

    public ShipType getShipType() {

        return (hasShip()) ? ship.getType() : null;
    }

    public boolean isHit() {
        return status == CellStatus.HIT;
    }

    public String display(boolean reveal) {

        if(status == CellStatus.HIT) {
            return "X";
        } else if(status == CellStatus.MISS) {
            return "A";
        } else if(reveal && status == CellStatus.SHIP) {
            return switch(ship.getType()) {
                case ShipType.LANCHA -> "L";
                case ShipType.BUQUE -> "B";
                case ShipType.ACORAZADO -> "Z";
                case ShipType.PORTAVIONES -> "P";
            };
        }else {
            return "-";
        }
    }
}
