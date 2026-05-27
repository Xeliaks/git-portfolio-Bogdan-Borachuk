package github.portfolio;

/**
 * Represents the 3x3 Tic Tac Toe board.
 * Each cell holds null (empty), "X", or "O".
 */
public class Board {

    private final String[][] cells;
    private static final int SIZE = 3;

    public Board() {
        cells = new String[SIZE][SIZE];
    }

    /** Returns the raw 2D cell array (used by Game for win/draw checks). */
    public String[][] getCells() {
        return cells;
    }

    /** Returns the symbol at the given position, or null if empty. */
    public String getCell(int row, int col) {
        return cells[row][col];
    }

    /** Places a symbol in a cell. Call isValidMove() first. */
    public void placeSymbol(int row, int col, String symbol) {
        cells[row][col] = symbol;
    }

    /** Returns true if the cell is within bounds and not already occupied. */
    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false;
        }
        return cells[row][col] == null;
    }
}
