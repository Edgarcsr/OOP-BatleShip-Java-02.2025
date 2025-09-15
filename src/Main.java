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
            System.out.println("🌊 Que comece a batalha naval! 🌊");
            game.play();
            if (game.onDeclaredWinner()) {
                System.out.println("🏆 O vencedor desta batalha naval é: " + game.getWinner().getName() + " 🏆");
                System.out.println("🗺️  Este é o mapa final do inimigo:");
                game.getLoser().getBoard().printBoard(true);
                menu.printResults();
            }
        }

    }
}
