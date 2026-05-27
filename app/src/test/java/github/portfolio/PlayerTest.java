package github.portfolio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void playerStoresName() {
        Player player = new Player("Alice", "X");
        assertEquals("Alice", player.getName());
    }

    @Test
    void playerStoresSymbol() {
        Player player = new Player("Bob", "O");
        assertEquals("O", player.getSymbol());
    }

    @Test
    void toStringIncludesNameAndSymbol() {
        Player player = new Player("Alice", "X");
        String result = player.toString();
        assertTrue(result.contains("Alice"), "toString should include the player name");
        assertTrue(result.contains("X"), "toString should include the symbol");
    }
}
