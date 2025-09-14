package game;

import enums.Difficulty;
import enums.GameMode;
import model.Board;
import model.CpuPlayer;
import model.HumanPlayer;
import model.Player;

import java.util.Scanner;
import static utils.BoatsGenerator.generateBoatsList;
import static utils.DifficultValidator.intSelectOptionsGameValidator;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private static Menu instance;
    private Difficulty difficultySelected;
    private GameMode gameModeSelected;
    private String namePlayerOne = "";
    private String namePlayerTwo = "";
    private Player playerOne;
    private Player playerTwo;
    private Boolean endGame = false;
    private Boolean resetGame = false;
    private Boolean newGame = true;

    private Menu() {
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void showMenu() {
        String header = """
                             ______     ______     ______   __         ______  \s
                            /\\  == \\   /\\  __ \\   /\\__  _\\ /\\ \\       /\\  ___\\ \s
                            \\ \\  __<   \\ \\  __ \\  \\/_/\\ \\/ \\ \\ \\____  \\ \\  __\\ \s
                             \\ \\_____\\  \\ \\_\\ \\_\\    \\ \\_\\  \\ \\_____\\  \\ \\_____\\
                              \\/_____/   \\/_/\\/_/     \\/_/   \\/_____/   \\/_____/
                                                                               \s
                                      ______     __  __     __     ______      \s
                                     /\\  ___\\   /\\ \\_\\ \\   /\\ \\   /\\  == \\     \s
                                     \\ \\___  \\  \\ \\  __ \\  \\ \\ \\  \\ \\  _-/     \s
                                      \\/\\_____\\  \\ \\_\\ \\_\\  \\ \\_\\  \\ \\_\\       \s
                                       \\/_____/   \\/_/\\/_/   \\/_/   \\/_/        \
                """;
        System.out.println(header);
        System.out.println();
        System.out.println();
        System.out.println();
        String boat = """
                                                     |__
                                                     |\\/
                                                     ---
                                                     / | [
                                              !      | |||
                                            _/|     _/|-++'
                                        +  +--|    |--|--|_ |-
                                     { /|__|  |/\\__|  |--- |||__/
                                    +---------------___[}-_===_.'____               /\\
                                ____`-' ||___-{]_| _[}-  |     |_[___\\==--          \\/   _
                 __..._____--==/___]_|__|_____________________________[___\\==--___,-----' .7
                |    Henrico 081230027, Edgar 0812300, Nicholas 081230038, Vitor 0812300/
                 \\_______________________________________________________________________|""";
        System.out.println(boat);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                         Se prepare para batalha!");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("-------------------- PRESIONA ENTER PARA SAIR OU QUALQUER TECLA PARA CONTINUAR --------------------");

        if (!scanner.nextLine().isEmpty()) {
            setSelectOptionsGame("difficulty");
            showDifficultySelection(difficultySelected);
            setSelectOptionsGame("gameMode");
            setPlayerNames(gameModeSelected);
        } else {
           exitMessage();
        }
    }

    private void showDifficultySelection(Difficulty difficulty) {
        System.out.println("####################################################");
        System.out.printf("  Vocês escolheram a dificuldade %s \n", difficulty.getDifficulty());
        System.out.printf("  Tens %d disparos para usar e %d barcos de guerra \n", difficulty.getAttempts(), difficulty.getTotalQuantityBoats());
        System.out.println("####################################################");
        System.out.println();
    }

    private void setSelectOptionsGame(String caseSelection) {
        int intSelection;

        System.out.printf("|    Momento de escolher %s   |\n", caseSelection.equals("difficulty") ? "a dificuldade" : "o modo de jogo");
        System.out.println();
        System.out.println("|                                         |");
        System.out.println("|=========================================|");
        System.out.printf("|    %S             Número      |\n", (caseSelection.equals("difficulty") ? "Dificuldade" : "Modo de Jogo"));
        System.out.println("|    ----------             ------        |");
        System.out.printf("|    %s%s1           |\n", (caseSelection.equals("difficulty") ? "Covarde" : "Jogador vs Jogador"), caseSelection.equals("difficulty") ? getSpaces("Covarde") : getSpaces("Jogador vs Jogador"));
        System.out.printf("|    %s%s2           |\n", (caseSelection.equals("difficulty") ? "Aventureiro" : "Jogador vs CPU"), caseSelection.equals("difficulty") ? getSpaces("Aventureiro") : getSpaces("Jogador vs CPU"));
        System.out.printf("|    %s%s3           |\n", (caseSelection.equals("difficulty") ? "Destruidor" : "CPU vs CPU"), caseSelection.equals("difficulty") ? getSpaces("Destruidor") : getSpaces("CPU vs CPU"));
        System.out.println("|=========================================|");
        System.out.println();
        System.out.printf("Insira o número correspondente %s da sua partida:", (caseSelection.equals("difficulty")) ? "a Dificuldade" : "ao modo de jogo");
        intSelection = intSelectOptionsGameValidator(scanner);
        System.out.println();
        System.out.println();
        if (caseSelection.equals("difficulty")) {
            switch (intSelection) {
                case 1 -> this.difficultySelected = Difficulty.EASY;
                case 2 -> this.difficultySelected = Difficulty.MEDIUM;
                case 3 -> this.difficultySelected = Difficulty.HARD;
                default -> throw new IllegalStateException("Unexpected value: " + intSelection);
            }
        } else {
            switch (intSelection) {
                case 1 -> this.gameModeSelected = GameMode.PLAYERTOPLAYER;
                case 2 -> this.gameModeSelected = GameMode.PLAYERTOCPU;
                case 3 -> this.gameModeSelected = GameMode.CPUTOCPU;
            }
        }
        scanner.nextLine();
    }

    private String getSpaces(String word) {
        StringBuilder spacer = new StringBuilder();
        int length = word.length();
        spacer.append(" ".repeat(Math.max(0, 25 - length)));
        return spacer.toString();
    }

    private void setPlayerNames(GameMode gameModeSelected) {

        String temporalPlayerName = switch (difficultySelected) {
            case EASY -> "Covarde";
            case MEDIUM -> "Aventureiro";
            case HARD -> "Destruidor";
        };

        if(gameModeSelected != GameMode.CPUTOCPU) {
            System.out.println("-------------------------------------------------");
            System.out.println("   É hora de os jogadores se apresentarem!   ");
            System.out.println();
            System.out.println();
        } else {
            System.out.println("-------------------------------------------------");
            System.out.println("    Os robôs se preparem para a batalha!     ");
            System.out.println();
            System.out.println();
        }

        Board temporaryBoard = new Board(generateBoatsList(difficultySelected));
        switch (gameModeSelected) {
            case PLAYERTOPLAYER -> {
                System.out.printf("%s, insira seu nome:\n", temporalPlayerName);
                this.namePlayerOne = scanner.nextLine();
                if(this.namePlayerOne.isEmpty()) {
                    System.out.println("Não pode deixar o nome vazio. O nome padrão será atribuído.");
                    this.namePlayerOne = temporalPlayerName;
                }
                scanner.reset();
                System.out.printf("O outro %s, insira seu nome: \n", temporalPlayerName);
                this.namePlayerTwo = scanner.nextLine();
                if(this.namePlayerTwo.isEmpty()) {
                    System.out.println("Não pode deixar o nome vazio. O nome padrão será atribuído.");
                    this.namePlayerTwo = temporalPlayerName;
                }
                playerOne = (playerOne == null) ? new HumanPlayer(this.namePlayerOne, temporaryBoard, difficultySelected.getAttempts()) : playerOne;
                playerTwo = (playerTwo == null) ? new HumanPlayer(this.namePlayerTwo, temporaryBoard, difficultySelected.getAttempts()) : playerTwo;
            }
            case PLAYERTOCPU -> {
                scanner.reset();
                System.out.printf("%s, insira seu nome: ", temporalPlayerName);
                this.namePlayerOne = scanner.nextLine();
                if(this.namePlayerOne.isEmpty()) {
                    System.out.print("Não pode deixar o nome vazio. O nome padrão será atribuído.\n");
                    this.namePlayerOne = temporalPlayerName;
                }
                this.namePlayerTwo = temporalPlayerName.concat(" Robô");

                playerOne = (playerOne == null) ? new HumanPlayer(this.namePlayerOne, temporaryBoard, difficultySelected.getAttempts()) : playerOne;
                playerTwo = (playerTwo == null) ? new CpuPlayer(this.namePlayerTwo, temporaryBoard, difficultySelected.getAttempts()) : playerTwo;
            }
            case CPUTOCPU -> {
                this.namePlayerOne = temporalPlayerName.concat(" Robô One");
                this.namePlayerTwo = temporalPlayerName.concat(" Robô Two");

                playerOne = (playerTwo == null) ? new CpuPlayer(this.namePlayerOne, temporaryBoard, difficultySelected.getAttempts()) : playerOne;
                playerTwo = (playerTwo == null) ? new CpuPlayer(this.namePlayerTwo, temporaryBoard, difficultySelected.getAttempts()) : playerTwo;
            }
        }
    }

    private void clearMenuData() {
        this.difficultySelected = null;
        this.gameModeSelected = null;
        this.namePlayerOne = "";
        this.namePlayerTwo = "";
        this.playerOne = null;
        this.playerTwo = null;
        this.endGame = false;
        this.resetGame = false;
        this.newGame = true;
    }

    public Boolean onEndGame() {
        return endGame;
    }

    public boolean turnOffGame() {
        System.out.println("Quer sair do jogo? (S/N)");
        String response = scanner.nextLine().trim().toUpperCase();

        if (response.equals("S")) {
            exitMessage();
            return true;
        } else if (response.equals("N")) {
            System.out.println("Quer reiniciar a partida? S/N");
            String restartResponse = scanner.nextLine().trim().toUpperCase();
            if (restartResponse.equals("S")) {
                System.out.println("Perfeito! Vamos reiniciar o jogo.");
                resetGame = true;
                newGame = false;
            } else if (restartResponse.equals("N")) {
                clearMenuData();
                System.out.println("Perfeito! Vamos começar uma nova partida.");
            } else {
                System.out.println("Resposta inválida. Por favor, insira 'S' ou 'N'.");
                return turnOffGame();
            }
            return false;
        } else {
            System.out.println("Resposta inválida. Por favor, insira 'S' ou 'N'.");
            return turnOffGame();
        }


    }

    public boolean onNewGame() {
        return newGame;
    }

    public boolean onResetGame() {
        return resetGame;
    }

    public void exitMessage() {
        System.out.println("Muito obrigado por jogar, ate a próxima!");
        System.exit(0);
    }

    public void printResults() {
        scanner.reset();
        System.out.println("Gostaria de ver os dados do seu jogo? S/N");
        String response = scanner.nextLine().trim().toUpperCase();

        if (response.equals("S")) {
            if(playerOne.isWinner()) playerOne.getBoard().printPerformanceRank();
            else playerTwo.getBoard().printPerformanceRank();
            this.endGame = turnOffGame();
        } else if (response.equals("N")) {
            this.endGame = turnOffGame();
        } else {
            System.out.println("Resposta inválida. Por favor, insira 'S' ou 'N'.");
            printResults();
        }
    }

    public Difficulty getDifficultySelected () {
        return difficultySelected;
    }

    public Player getPlayerOne () {
        return switch (gameModeSelected) {
            case PLAYERTOPLAYER, PLAYERTOCPU -> (HumanPlayer) playerOne;
            case CPUTOCPU -> (CpuPlayer) playerOne;
        };
    }
    public Player getPlayerTwo () {
        return switch (gameModeSelected) {
            case PLAYERTOPLAYER -> (HumanPlayer) playerTwo;
            case PLAYERTOCPU, CPUTOCPU -> (CpuPlayer) playerTwo;
        };
    }
}
