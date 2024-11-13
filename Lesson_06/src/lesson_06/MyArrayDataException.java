package lesson_06;

public class MyArrayDataException extends Exception {
	private int row;
    private int column;

    public MyArrayDataException(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
