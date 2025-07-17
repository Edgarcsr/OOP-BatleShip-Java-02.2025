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
                |                                                           PEPINACO BBe-61/
                 \\_______________________________________________________________________|""";
        System.out.println(boat);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                         ¡Prepárate para la batalla!");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("-------------------- PRESIONA ENTER PARA SALIR O CUALQUIER TECLA PARA CONTINUAR PARA CONTINUAR --------------------");

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
        System.out.printf("  Has seleccionado la dificultad %s \n", difficulty.getDifficulty());
        System.out.printf("  Tienes %d disparos para hundir %d barcos de guerra \n", difficulty.getAttempts(), difficulty.getTotalQuantityBoats());
        System.out.println("####################################################");
        System.out.println();
    }

    private void setSelectOptionsGame(String caseSelection) {
        int intSelection;

        System.out.printf("|    Momento de elegir %s        |\n", caseSelection.equals("difficulty") ? "la dificultad" : "el modo de juego");
        System.out.println();
        System.out.println("|                                         |");
        System.out.println("|=========================================|");
        System.out.printf("|    %S             Número        |\n", (caseSelection.equals("difficulty") ? "Dificultad" : "Modo de Juego"));
        System.out.println("|    ----------             ------        |");
        System.out.printf("|    %s%s1           |\n", (caseSelection.equals("difficulty") ? "Cobarde" : "Jugador vs Jugador"), caseSelection.equals("difficulty") ? getSpaces("Cobarde") : getSpaces("Jugador vs Jugador"));
        System.out.printf("|    %s%s2           |\n", (caseSelection.equals("difficulty") ? "Aventurero" : "Jugador vs CPU"), caseSelection.equals("difficulty") ? getSpaces("Aventurero") : getSpaces("Jugador vs CPU"));
        System.out.printf("|    %s%s3           |\n", (caseSelection.equals("difficulty") ? "Destructor" : "CPU vs CPU"), caseSelection.equals("difficulty") ? getSpaces("Destructor") : getSpaces("CPU vs CPU"));
        System.out.println("|=========================================|");
        System.out.println();
        System.out.printf("Introduce el numero correspondiente %s de tu partida:", (caseSelection.equals("difficulty")) ? "a la Dificultad" : "al modo de juego");
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
            case EASY -> "Cobarde";
            case MEDIUM -> "Aventurero";
            case HARD -> "Destructor";
        };

        if(gameModeSelected != GameMode.CPUTOCPU) {
            System.out.println("-------------------------------------------------");
            System.out.println("   ¡Es hora de que los jugadores se presenten!   ");
            System.out.println();
            System.out.println();
        } else {
            System.out.println("-------------------------------------------------");
            System.out.println("    ¡Los robots se preparan para la batalla!     ");
            System.out.println();
            System.out.println();
        }

        Board temporaryBoard = new Board(generateBoatsList(difficultySelected));
        switch (gameModeSelected) {
            case PLAYERTOPLAYER -> {
                System.out.printf("%s, introduce tu nombre:\n", temporalPlayerName);
                this.namePlayerOne = scanner.nextLine();
                if(this.namePlayerOne.isEmpty()) {
                    System.out.println("No puedes dejar el nombre vacío. Se asignará el nombre predeterminado.");
                    this.namePlayerOne = temporalPlayerName;
                }
                scanner.reset();
                System.out.printf("El otro %s, introduce tu nombre: \n", temporalPlayerName);
                this.namePlayerTwo = scanner.nextLine();
                if(this.namePlayerTwo.isEmpty()) {
                    System.out.println("No puedes dejar el nombre vacío. Se asignará el nombre predeterminado.");
                    this.namePlayerTwo = temporalPlayerName;
                }
                playerOne = (playerOne == null) ? new HumanPlayer(this.namePlayerOne, temporaryBoard, difficultySelected.getAttempts()) : playerOne;
                playerTwo = (playerTwo == null) ? new HumanPlayer(this.namePlayerTwo, temporaryBoard, difficultySelected.getAttempts()) : playerTwo;
            }
            case PLAYERTOCPU -> {
                scanner.reset();
                System.out.printf("%s, introduce tu nombre: ", temporalPlayerName);
                this.namePlayerOne = scanner.nextLine();
                if(this.namePlayerOne.isEmpty()) {
                    System.out.print("No puedes dejar el nombre vacío. Se asignará el nombre predeterminado.\n");
                    this.namePlayerOne = temporalPlayerName;
                }
                this.namePlayerTwo = temporalPlayerName.concat(" Robotito");

                playerOne = (playerOne == null) ? new HumanPlayer(this.namePlayerOne, temporaryBoard, difficultySelected.getAttempts()) : playerOne;
                playerTwo = (playerTwo == null) ? new CpuPlayer(this.namePlayerTwo, temporaryBoard, difficultySelected.getAttempts()) : playerTwo;
            }
            case CPUTOCPU -> {
                this.namePlayerOne = temporalPlayerName.concat(" Robotito One");
                this.namePlayerTwo = temporalPlayerName.concat(" Robotito Two");

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
        System.out.println("¿Quieres salir del juego? (S/N)");
        String response = scanner.nextLine().trim().toUpperCase();

        if (response.equals("S")) {
            exitMessage();
            return true;
        } else if (response.equals("N")) {
            System.out.println("¿Quieres reinicia la partida? S/N");
            String restartResponse = scanner.nextLine().trim().toUpperCase();
            if (restartResponse.equals("S")) {
                System.out.println("¡Perfecto! Vamos a reiniciar el juego.");
                resetGame = true;
                newGame = false;
            } else if (restartResponse.equals("N")) {
                clearMenuData();
                System.out.println("¡Perfecto! Vamos a empezar una nueva partida.");
            } else {
                System.out.println("Respuesta no válida. Por favor, introduce 'S' o 'N'.");
                return turnOffGame();
            }
            return false;
        } else {
            System.out.println("Respuesta no válida. Por favor, introduce 'S' o 'N'.");
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
        System.out.println("Gracias por jugar, ¡hasta la próxima!");
        System.exit(0);
    }

    public void printResults() {
        scanner.reset();
        System.out.println("¿Te gustaría ver tu partida en datos? S/N");
        String response = scanner.nextLine().trim().toUpperCase();

        if (response.equals("S")) {
            if(playerOne.isWinner()) playerOne.getBoard().printPerformanceRank();
            else playerTwo.getBoard().printPerformanceRank();
            this.endGame = turnOffGame();
        } else if (response.equals("N")) {
            this.endGame = turnOffGame();
        } else {
            System.out.println("Respuesta no válida. Por favor, introduce 'S' o 'N'");
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
