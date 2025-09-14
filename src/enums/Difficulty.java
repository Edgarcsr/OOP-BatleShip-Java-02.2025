package enums;

public enum Difficulty {
    EASY(5, 3, 1, 1, 50, 10),
    MEDIUM(2, 1, 1, 1, 50, 15),
    HARD(1, 1, 0, 0, 50, 30);

    final int lanchas,
        buques,
        acorazados,
        portaaviones,
        attempts,
        boardSize;

    Difficulty(int lanchas, int buques, int acorazados, int portaaviones, int attempts, int boardSize) {
        this.lanchas = lanchas;
        this.buques = buques;
        this.acorazados = acorazados;
        this.portaaviones = portaaviones;
        this.attempts = attempts;
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getQuantityKindOfBoat(ShipType shipType) {
        return switch (shipType) {
            case BOAT -> lanchas;
            case SHIP -> buques;
            case BATTLESHIP -> acorazados;
            case AIRCRAFTCARRIER -> portaaviones;
        };
    }

    public int getTotalQuantityBoats() {
        return lanchas + buques + acorazados + portaaviones;
    }

    public Difficulty getDifficulty() {
        return this;
    }
}
