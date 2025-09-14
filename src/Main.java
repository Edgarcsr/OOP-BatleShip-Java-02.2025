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
                System.out.println("Reiniciando o jogo...");
                game.resetGame();
            }
            assert game != null;
            System.out.println("Que começe o jogo!");
            game.play();
            if (game.onDeclaredWinner()) {
                System.out.println("O ganhador é: " + game.getWinner().getName());
                System.out.println("Esse é o tabuleiro inimigo:");
                game.getLoser().getBoard().printBoard(true);
                menu.printResults();
            }
        }

    }
}
