package github.portfolio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void newBoardHasAllCellsEmpty() {
        for (String[] row : board.getCells()) {
            for (String cell : row) {
                assertNull(cell, "Every cell should be null on a new board");
            }
        }
    }

    @Test
    void validMoveOnEmptyCellReturnsTrue() {
        assertTrue(board.isValidMove(0, 0));
        assertTrue(board.isValidMove(1, 1));
        assertTrue(board.isValidMove(2, 2));
    }

    @Test
    void invalidMoveOnOccupiedCellReturnsFalse() {
        board.placeSymbol(1, 1, "X");
        assertFalse(board.isValidMove(1, 1), "Cell (1,1) is occupied — move should be invalid");
    }

    @Test
    void invalidMoveOutOfBoundsReturnsFalse() {
        assertFalse(board.isValidMove(-1, 0));
        assertFalse(board.isValidMove(0, 3));
        assertFalse(board.isValidMove(3, 3));
    }

    @Test
    void placeSymbolFillsCell() {
        board.placeSymbol(0, 2, "O");
        assertEquals("O", board.getCell(0, 2));
    }

    @Test
    void isFullReturnsFalseWhenBoardHasEmptyCells() {
        board.placeSymbol(0, 0, "X");
        assertFalse(board.isFull());
    }

    @Test
    void isFullReturnsTrueWhenAllCellsFilled() {
        String[] symbols = {"X", "O", "X", "O", "X", "O", "O", "X", "O"};
        int k = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board.placeSymbol(r, c, symbols[k++]);
            }
        }
        assertTrue(board.isFull());
    }
}
