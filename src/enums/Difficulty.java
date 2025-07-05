package enums;
/*
* Fácil: El ordenador colocará 10 barcos (5 lanchas, 3 buques, 1
acorazado y 1 portaaviones) en el tablero y el jugador tendrá 50
intentos para hundirlos todos.
 Medio: El ordenador colocará 5 barcos (2 lanchas, 1 buque, 1
acorazado y 1 portaaviones) en el tablero y el jugador tendrá 30
intentos para hundirlos todos.
 Difícil: El ordenador colocará 2 barcos (1 lancha y 1 buque) en el
tablero y el jugador tendrá 10 intentos para hundirlos todos.
 Personalizado: Se le preguntará al usuario el tamaño del tablero,
el número de barcos de cada tipo y el número de intentos.*/

/*
*  LANCHA(1),
    BUQUE(3),
    ACORAZADO(4),
    PORTAVIONES(5);*/


public enum Difficulty {
    EASY(5, 3, 1, 1, 50, 10),
    MEDIUM(2, 1, 1, 1, 30, 10),
    HARD(1, 0, 0, 0, 10, 10);

    final int lanchas,
        buques,
        acorazados,
        portaaviones,
        attempts,
        boardSize;


    Difficulty(int lanchas, int buques, int acorazados, int portaaviones, int attempts, int boardSize) {
        this.lanchas = lanchas;
        this.buques = buques;
        this.acorazados = acorazados;
        this.portaaviones = portaaviones;
        this.attempts = attempts;
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getQuantityOfBoats( ShipType shipType) {
        return switch (shipType) {
            case LANCHA -> lanchas;
            case BUQUE -> buques;
            case ACORAZADO -> acorazados;
            case PORTAVIONES -> portaaviones;
        };
    }

    public int getTotalSize() {
        return lanchas + buques + acorazados + portaaviones;
    }

    public Difficulty getDifficulty() {
        return this;
    }
}
