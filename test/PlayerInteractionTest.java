import enums.Difficulty;
import model.Board;
import model.CpuPlayer;
import model.HumanPlayer;

import static utils.BoatsGenerator.generateBoatsList;


public class PlayerInteractionTest {
    public static void main(String[] args) {
        Difficulty difficulty = Difficulty.EASY;

        Board board1 = new Board(difficulty.getBoardSize(), difficulty.getTotalNumberOfBoats(), generateBoatsList(difficulty));
        board1.placeShips();
        Board board2 = new Board(difficulty.getBoardSize(), difficulty.getTotalNumberOfBoats(), generateBoatsList(difficulty));
        board2.placeShips();

        HumanPlayer Juan = new HumanPlayer("Juan", board1, difficulty.getAttempts());
        CpuPlayer robotito = new CpuPlayer("Robotito", board2, difficulty.getAttempts());

        robotito.nextShot(Juan.getBoard());
        Juan.getBoard().printBoard(true);
        Juan.nextShot(robotito.getBoard());
        robotito.getBoard().printBoard(true);
    }
}
