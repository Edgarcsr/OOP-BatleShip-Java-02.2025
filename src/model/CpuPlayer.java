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
        // Para CPU, usar o primeiro navio disponível com poder especial se houver
        Ship selectedShip = board.getShips().stream()
                .filter(ship -> !ship.isSunk())
                .filter(Ship::hasSpecialPower)
                .findFirst()
                .orElse(board.getShips().stream()
                        .filter(ship -> !ship.isSunk())
                        .findFirst()
                        .orElse(null));
        
        if (selectedShip != null) {
            nextShot(enemyBoard, selectedShip);
        }
    }

    @Override
    public void nextShot(Board enemyBoard, Ship selectedShip) {
        if (attempts > 0) {
            int shotCount = selectedShip.getShotCount();
            
            if (selectedShip.hasSpecialPower()) {
                System.out.printf("🤖 CPU está usando o %s com poder especial (%d torpedos)! 🤖\n", 
                                selectedShip.getType().name(), shotCount);
            }
            
            for (int i = 0; i < shotCount && attempts > 0; i++) {
                Coordinate temporaryShot = getShotCoordinate(randomCellGenerator.getCell());

                while (temporaryShot.equals(lastShot)) {
                    temporaryShot = getShotCoordinate(randomCellGenerator.getCell());
                }

                Coordinate currentShot = temporaryShot;
                lastShot = currentShot;

                enemyBoard.registryShot(currentShot);
                attempts--;
                checkAttempts();
                
                if (attempts <= 0) break;
            }
        } else {
            System.out.println("Acabou suas tentativas, mais sorte da próxima vez!");
        }
    }


    public Coordinate getShotCoordinate(Cell cellTemporary) {
        return cellTemporary.getCoordinate();
    }

}
