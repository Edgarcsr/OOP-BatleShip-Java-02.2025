package model;

public abstract class Player {
    protected String name;
    protected Board board;
    protected int attempts;
    private boolean spentAllAttempts = false;
    protected PlayerListener listener;
    private boolean winner = false;

    public Player(String name, Board board, int attempts) {
        this.name = name;
        this.board = board;
        this.attempts = attempts;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isSpentAllAttempts() {
        return spentAllAttempts;
    }

    protected void checkAttempts() {
        if (attempts <= 0 && !spentAllAttempts) {
            spentAllAttempts = true;

            if (listener != null) {
                listener.onNoAttemptsLeft(this);
            }
        }
    }
    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean isWinner() {
        return  winner;
    }

    public void reset(int attempts) {
        this.winner = false;
        this.attempts = attempts;
        this.spentAllAttempts = false;
    }

    public abstract void nextShot(Board enemyBoard, Ship selectedShip);

    public void nextShot(Board enemyBoard) {
        // Método mantido para compatibilidade, usa o primeiro navio disponível
        Ship firstAvailableShip = board.getShips().stream()
                .filter(ship -> !ship.isSunk())
                .findFirst()
                .orElse(null);
        if (firstAvailableShip != null) {
            nextShot(enemyBoard, firstAvailableShip);
        }
    }
}
