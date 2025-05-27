package utils;
import model.Board;
import model.Cell;


class RandomCellGenerator {
    private final Cell cell;

    public RandomCellGenerator(Board board) {
        int max = board.getSize();
        int randomRow = (int) (Math.random() * (max + 1));
        int randomColumn = (int) (Math.random() * (max + 1));
        this.cell = board.getCell(randomRow, randomColumn);
    }

    public Cell getCell() {
        return this.cell;
    }
}
