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

    public void processHit( Coordinate coords ) {

        if(status == CellStatus.EMPTY) {
            status = CellStatus.MISS;
        } else if( status == CellStatus.SHIP) {
            ship.registryHitAt(coords);
            status = CellStatus.HIT;
        }
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setShip(Ship ship) {

        this.ship = ship;
        this.status = CellStatus.SHIP;
    }

    public Ship getShip() {
        return this.ship;
    }

    public boolean hasShip() {
        return status != CellStatus.EMPTY && status != CellStatus.MISS;
    }

    public CellStatus getCellStatus() {
        return this.status;
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

    protected void reset() {
        this.ship = null;
        this.status = CellStatus.EMPTY;
    }
}
