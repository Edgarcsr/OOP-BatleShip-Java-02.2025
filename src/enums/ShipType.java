package enums;

public enum ShipType {
    BOAT(1),
    SHIP(3),
    BATTLESHIP(4),
    AIRCRAFTCARRIER(5);
    
    private final int size;

    ShipType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
