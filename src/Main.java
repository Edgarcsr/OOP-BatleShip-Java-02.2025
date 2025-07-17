import game.Game;
import game.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = Menu.getInstance();
        Game game = null;

        while (!menu.onEndGame()) {
            if (menu.onNewGame()) {
                menu.showMenu();
                game = new Game(menu.getDifficultySelected(), menu.getPlayerOne(), menu.getPlayerTwo());
                game.setupGame();
            } else if (menu.onResetGame()) {
                assert game != null;
                System.out.println("Reiniciando el juego...");
                game.resetGame();
            }
            assert game != null;
            System.out.println("Comienza el juego!");
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
