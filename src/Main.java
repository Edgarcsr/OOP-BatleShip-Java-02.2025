import model.Game;
import model.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = Menu.getInstance();
        Game game;

        while (!menu.onEndGame()) {
            System.out.println("Welcome to the Battleship Game!");
            menu.showMenu();
            game = new Game( menu.getDifficultySelected(), menu.getPlayerOne(), menu.getPlayerTwo());
            game.setupGame();
            game.play();
            if (game.onDeclaredWinner()) {
                System.out.println("El ganador es: " + game.getWinner().getName());
                System.out.println("Este es el tablero enemigo:");
                game.getLoser().getBoard().printBoard(true);
                menu.printResults();
            }
        }

    }
}
