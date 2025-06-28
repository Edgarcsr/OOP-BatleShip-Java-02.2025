import model.*;
import enums.*;
import utils.*;
import static utils.BoatsGenerator.generateBoatsList;


public class PlayerInteractionTest {
    public static void main(String[] args) {
        Difficulty difficulty = Difficulty.EASY;

        Board board1 = new Board(difficulty.getBoardSize(), difficulty.getTotalSize(), generateBoatsList(difficulty));
        board1.placeShips();
        Board board2 = new Board(difficulty.getBoardSize(), difficulty.getTotalSize(), generateBoatsList(difficulty));
        board2.placeShips();

        HumanPlayer Juan = new HumanPlayer("Juan", board1, difficulty.getAttempts());
        CpuPlayer robotito = new CpuPlayer("Robotito", board2, difficulty.getAttempts());

        robotito.getBoard().printBoard(true);
        Juan.nextShot(robotito.getBoard());
        Juan.nextShot(robotito.getBoard());
        Juan.nextShot(robotito.getBoard());
        robotito.getBoard().printBoard(false);
    }
}
