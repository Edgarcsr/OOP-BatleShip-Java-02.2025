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
    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean isWinner() {
        return  winner;
    }
    public abstract void nextShot(Board enemyBoard);
}
