package enums;

public enum Difficulty {
    EASY(1, 1, 1, 1, 2, 50, 10),
    MEDIUM(1, 1, 1, 1, 2, 50, 15),
    HARD(1, 1, 1, 1, 2, 50, 30);

    final int carrier,
        battleship,
        cruiser,
        submarine,
        destroyer,
        attempts,
        boardSize;

    Difficulty(int carrier, int battleship, int cruiser, int submarine, int destroyer, int attempts, int boardSize) {
        this.carrier = carrier;
        this.battleship = battleship;
        this.cruiser = cruiser;
        this.submarine = submarine;
        this.destroyer = destroyer;
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
            case CARRIER -> carrier;
            case BATTLESHIP -> battleship;
            case CRUISER -> cruiser;
            case SUBMARINE -> submarine;
            case DESTROYER -> destroyer;
        };
    }

    public int getTotalQuantityBoats() {
        return carrier + battleship + cruiser + submarine + destroyer;
    }

    public Difficulty getDifficulty() {
        return this;
    }
}
