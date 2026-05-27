package github.portfolio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    private Player playerX;
    private Player playerO;

    @BeforeEach
    void setUp() {
        playerX = new Player("Alice", "X");
        playerO = new Player("Bob", "O");
        game = new Game(playerX, playerO);
    }

    @Test
    void noWinnerOnNewGame() {
        assertNull(game.checkWinner());
    }

    @Test
    void detectsHorizontalWin() {
        // X fills the top row
        game.makeMove(0, 0); // X
        game.makeMove(1, 0); // O
        game.makeMove(0, 1); // X
        game.makeMove(1, 1); // O
        game.makeMove(0, 2); // X wins top row
        assertEquals("X", game.checkWinner());
    }

    @Test
    void detectsVerticalWin() {
        // O fills the middle column
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 0); // X
        game.makeMove(1, 1); // O
        game.makeMove(2, 2); // X
        game.makeMove(2, 1); // O wins middle column
        assertEquals("O", game.checkWinner());
    }

    @Test
    void detectsDiagonalWin() {
        // X fills main diagonal
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(1, 1); // X
        game.makeMove(0, 2); // O
        game.makeMove(2, 2); // X wins diagonal
        assertEquals("X", game.checkWinner());
    }

    @Test
    void detectsDraw() {
        // Fill board with no winner: X O X / O O X / X X O
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(0, 2); // X
        game.makeMove(1, 1); // O
        game.makeMove(1, 0); // X
        game.makeMove(2, 0); // O
        game.makeMove(1, 2); // X
        game.makeMove(2, 2); // O
        game.makeMove(2, 1); // X — board full, no winner
        assertEquals(GameResult.DRAW, game.getResult());
    }

    @Test
    void invalidMoveReturnsFalse() {
        game.makeMove(0, 0); // X
        boolean result = game.makeMove(0, 0); // same cell — should fail
        assertFalse(result, "Placing on an occupied cell should return false");
    }

    @Test
    void gameResultIsInProgressAtStart() {
        assertEquals(GameResult.IN_PROGRESS, game.getResult());
    }
}
