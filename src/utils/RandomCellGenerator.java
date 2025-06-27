package utils;

import model.Board;
import model.Cell;


public class RandomCellGenerator {
    private int  randomRow;
    private int  randomColumn;
    private int max;
    private Board board;


    public RandomCellGenerator(Board board) {
        this.max = board.getSize()-1;
        this.board = board;
    }

    public Cell getCell() {
        randomRow = (int) (Math.random() * max);
        randomColumn = (int) (Math.random() * max);

        return board.getCell(randomRow, randomColumn);
    }
}
