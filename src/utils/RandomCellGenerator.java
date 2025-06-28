package utils;

import model.Board;
import model.Cell;


public class RandomCellGenerator {
    private final int max;
    private final Board board;


    public RandomCellGenerator(Board board) {
        this.max = board.getSize()-1;
        this.board = board;
    }

    public Cell getCell() {
        int randomRow = (int) (Math.random() * max);
        int randomColumn = (int) (Math.random() * max);

        return board.getCell(randomRow, randomColumn);
    }
}
