package enums;



public enum ShipType {
    LANCHA(1),
    BUQUE(3),
    ACORAZADO(4),
    PORTAVIONES(5);
    
    private final int size;

    ShipType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
