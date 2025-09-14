package game;

import enums.Difficulty;
import model.Board;
import model.Player;

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
            showBoards(playerOne);

            if (!playerOne.isSpentAllAttempts()) {
                playerOne.nextShot(playerTwo.getBoard());
                if (!playerTwo.getBoard().getDestructiveShoots().isEmpty()) {
                    if (playerTwo.getBoard().getLastDestructiveShoot()) {
                        declareWinner(playerOne);
                        System.out.println("Foi escolhido um vencedor e ele não esgotou suas tentativas.\n" +
                                "\n");
                        declareLosser( playerOne.isWinner() ? playerTwo : playerOne );
                    }
                }
            } else if (playerOne.isSpentAllAttempts()) {
                System.out.println("O jogador " + playerOne.getName() + " esgotou suas tentativas.");
                declareWinner(playerTwo);
                declareLosser( playerOne.isWinner() ? playerTwo : playerOne );
            }
        }
    }

    private void showBoards(Player player) {
        System.out.println("Tabuleiro de " + player.getName() + ":");
        player.getBoard().printBoard(true);
    }

    private void declareWinner(Player winner) {
        this.winner = winner;
        winner.setWinner(true);
    }

    private void declareLosser(Player loser ) {
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
        playerOne.reset(difficulty.getAttempts());
        playerTwo.reset(difficulty.getAttempts());
    }

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }
}
