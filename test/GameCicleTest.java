import enums.Difficulty;
import model.Board;
import model.CpuPlayer;
import model.Game;
import model.HumanPlayer;

import static utils.BoatsGenerator.generateBoatsList;

public class GameCicleTest {
    public static void main(String[] args) {
    Difficulty difficulty = Difficulty.HARD;

        Board boardPlayerOne = new Board(difficulty.getBoardSize(), difficulty.getBoardSize(), generateBoatsList(difficulty));
        Board boardPlayerTwo = new Board(difficulty.getBoardSize(), difficulty.getBoardSize(), generateBoatsList(difficulty));

        HumanPlayer juan = new HumanPlayer("Player One", boardPlayerOne,  difficulty.getAttempts() );
        CpuPlayer carlos = new CpuPlayer("Player Two", boardPlayerTwo, difficulty.getAttempts() );

        Game game = new Game(difficulty, juan, carlos);
        game.setupGame();
        game.play();
    }
}
