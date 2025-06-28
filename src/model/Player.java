package model;

public abstract class Player {
    protected String name;
    protected final Board board;
    protected int attempts;
    private boolean spentAllAttempts = false;
    protected PlayerListener listener;

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

    public int getAttempts() {
        return attempts;
    }

    public void setPlayerListener(PlayerListener listener) {
        this.listener = listener;
    }

    protected void checkAttempts() {
        if (attempts <= 0 && !spentAllAttempts) {
            spentAllAttempts = true;
            if (listener != null) {
                listener.onNoAttemptsLeft(this);
            }
        }
    }

    public abstract void nextShot(Board enemyBoard);
}
