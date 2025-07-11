package model;

import enums.Difficulty;
import static utils.BoatsGenerator.generateBoatsList;

public class Game {
    private final Player playerOne;
    private final Player playerTwo;
    private final Difficulty difficulty;
    private Player winner;
    private Player loser;

    public Game (Difficulty difficulty, Player playerHuman, Player playerComputer) {
        this.difficulty = difficulty;
        this.playerOne = playerHuman;
        this.playerTwo = playerComputer;
    }

    public void setupGame() {
        Board boardPlayerOne = new Board(difficulty.getBoardSize(), difficulty.getBoardSize(), generateBoatsList(difficulty));
        boardPlayerOne.placeShips();
        playerOne.setBoard(boardPlayerOne);
        Board boardPlayerTwo = new Board(difficulty.getBoardSize(), difficulty.getBoardSize(), generateBoatsList(difficulty));
        boardPlayerTwo.placeShips();
        playerTwo.setBoard(boardPlayerTwo);
        playerOne.setWinner(false);
        playerTwo.setWinner(false);
    }

    public void play() {
        while (!onDeclaredWinner()) {
            turn(playerOne, playerTwo);
            if (onDeclaredWinner()) break;
            turn(playerTwo, playerOne);
        }
    }

    private void turn(Player playerOne, Player playerTwo) {
        if (!playerOne.isWinner() && !playerTwo.isWinner()) {
            showBoards(playerTwo);

            if (!playerOne.isSpentAllAttempts()) {
                playerOne.nextShot(playerTwo.getBoard());
                if (!playerTwo.getBoard().getDestructiveShoots().isEmpty()) {
                    if (playerTwo.getBoard().getLastDestructiveShoot()) {
                        System.out.println(playerOne.getName()+ " su ultimo disparo destruyó todos los barcos" + playerOne.getBoard().getDestructiveShoots().toString());
                        setWin(playerOne);
                        System.out.println("He asignado un ganador y no agotó sus intentos" + winner.getName());
                        setLoser( playerOne.isWinner() ? playerTwo : playerOne );
                        System.out.println("He asignado un perdedor" + loser.getName());
                    }
                }
            } else if (playerOne.isSpentAllAttempts()) {
                System.out.println("El jugador " + playerOne.getName() + " ha agotado sus intentos.");
                setWin(playerTwo);
                System.out.println("He asignado un ganador y no agotó sus intentos " + winner.getName());
                setLoser( playerOne.isWinner() ? playerTwo : playerOne );
                System.out.println("He asignado un perdedor " + loser.getName());
            }
        }
    }

    private void showBoards(Player player) {
        System.out.println("Tablero de " + player.getName() + ":");
        player.getBoard().printBoard(true);
    }

    private void setWin(Player winner) {
        this.winner = winner;
        winner.setWinner(true);
    }

    private void setLoser( Player loser ) {
        this.loser = loser;
    }

    public boolean onDeclaredWinner() {
        return winner != null;
    }

    public void resetGame() {
        this.winner = null;
        this.loser = null;
        playerOne.getBoard().resetBoard();
        playerTwo.getBoard().resetBoard();
    }

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }
}
