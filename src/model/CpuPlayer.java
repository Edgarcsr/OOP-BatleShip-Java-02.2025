package model;


import utils.RandomCellGenerator;


public class CpuPlayer extends Player {
    private Coordinate lastShot = new Coordinate(0,0);
    private final RandomCellGenerator randomCellGenerator;

    public CpuPlayer(String name , Board board, int attempts) {
        super(name ,board, attempts);
        this.randomCellGenerator = new RandomCellGenerator(board);
    }

    @Override
    public void nextShot(Board enemyBoard) {

        if (attempts > 0) {
           Coordinate temporaryShot = getShotCoordinate(randomCellGenerator.getCell());

           while (temporaryShot.equals(lastShot)) {
                temporaryShot = getShotCoordinate(randomCellGenerator.getCell());
            }

            Coordinate currentShot = temporaryShot;
            lastShot = currentShot;

            enemyBoard.registryShot(currentShot);
            attempts--;
            checkAttempts();
        } else {
            System.out.println("Has agotado tus intentos, ¡mejor suerte la próxima vez!");
        }
    }


    public Coordinate getShotCoordinate(Cell cellTemporary) {
        return cellTemporary.getCoordinate();
    }

}
