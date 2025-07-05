package model;

import enums.Difficulty;

public class Game {
    private final Player playerOne;
    private final Player playerTwo;
    private Difficulty difficulty;
    private Player winner;
    private Player loser;

    public Game (Difficulty difficulty, HumanPlayer playerHuman, CpuPlayer playerComputer) {
        this.difficulty = difficulty;
        this.playerOne = playerHuman;
        this.playerTwo = playerComputer;
    }

    public Game (Difficulty difficulty, HumanPlayer playerOne, HumanPlayer playerTwo) {
        this.difficulty = difficulty;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public Game (Difficulty difficulty, CpuPlayer playerOne, CpuPlayer playerTwo) {
        this.difficulty = difficulty;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void setupGame() {
        playerOne.setWinner(false);
        playerTwo.setWinner(false);
        Board boardPlayerOne = playerOne.getBoard();
        boardPlayerOne.placeShips();
        playerOne.setBoard(boardPlayerOne);
        Board boardPlayerTwo = playerTwo.getBoard();
        boardPlayerTwo.placeShips();
        playerTwo.setBoard(boardPlayerTwo);
    }

    public void play() {
        while (!onDeclaredWinner()) {
            turn(playerOne, playerTwo);
            turn(playerTwo, playerOne);
        }
    }

    private void turn(Player playerOne, Player playerTwo) {
        if (!playerOne.isWinner() && !playerTwo.isWinner()) {
            showBoards(playerTwo);

            if (!playerOne.isSpentAllAttempts()) {
                playerOne.nextShot(playerTwo.getBoard());
                if (!playerTwo.getBoard().getDestructiveShoots().isEmpty()) {
                    if (playerTwo.getBoard().getLastDestructiveShoot()) {
                        System.out.println(playerOne.getName()+ " su ultimo disparo destruyó todos los barcos" + playerOne.getBoard().getDestructiveShoots().toString());
                        setWin(playerOne);
                        System.out.println("He asignado un ganador y no agotó sus intentos" + winner.getName());
                        setLoser( playerOne.isWinner() ? playerTwo : playerOne );
                        System.out.println("He asignado un perdedor" + loser.getName());
                    }
                }
            } else if (playerOne.isSpentAllAttempts()) {
                System.out.println("El jugador " + playerOne.getName() + " ha agotado sus intentos.");
                setWin(playerTwo);
                System.out.println("He asignado un ganador y agotó sus intentos" + winner.getName());
                setLoser( playerOne.isWinner() ? playerTwo : playerOne );
                System.out.println("He asignado un perdedor" + loser.getName());
            }
        }
    }

    private void showBoards(Player player) {
        System.out.println("Tablero de " + player.getName() + ":");
        player.getBoard().printBoard(true);
    }

    public boolean onDeclaredWinner() {
       return winner != null;
    }

    private void setWin(Player winner) {
        winner.setWinner(true);
        this.winner = winner;
    }

    private void setLoser( Player loser ) {
        this.loser = loser;
    }

//    public void setDifficulty() {
//        Scanner scanner = new Scanner(System.in);
//        int option = 0;
//
//        System.out.print("Seleccione la dificultad del juego:\n" +
//                "1. Fácil\n" +
//                "2. Medio\n" +
//                "3. Difícil\n");
//        option = scanner.nextInt();
//
//        switch (option) {
//            case 1 -> this.difficulty = Difficulty.EASY;
//            case 2 -> this.difficulty = Difficulty.MEDIUM;
//            case 3 -> this.difficulty =  Difficulty.HARD;
//        };
//    }
}
