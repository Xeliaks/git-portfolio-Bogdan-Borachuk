package github.portfolio;

/**
 * Represents one of the two Tic Tac Toe players.
 * Each player has a display name and a symbol (X or O).
 */
public class Player {

    private final String name;
    private final String symbol;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}
