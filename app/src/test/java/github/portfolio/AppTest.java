package github.portfolio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Smoke tests verifying that the core classes can be instantiated
 * and wired together without throwing exceptions.
 */
class AppTest {

    @Test
    void gameCanBeCreatedWithTwoPlayers() {
        Player p1 = new Player("Alice", "X");
        Player p2 = new Player("Bob", "O");
        Game game = new Game(p1, p2);
        assertNotNull(game, "Game should be created successfully");
        assertEquals(GameResult.IN_PROGRESS, game.getResult());
    }

    @Test
    void boardCanBeCreatedAndAcceptsMoves() {
        Board board = new Board();
        assertTrue(board.isValidMove(0, 0));
        board.placeSymbol(0, 0, "X");
        assertFalse(board.isValidMove(0, 0));
    }
}
