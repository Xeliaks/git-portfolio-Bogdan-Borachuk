package github.portfolio;

/**
 * Represents the possible outcomes of a Tic Tac Toe game.
 */
public enum GameResult {
    /** A player has three symbols in a row, column, or diagonal. */
    WIN,

    /** All cells are filled and no player has won. */
    DRAW,

    /** The game is still in progress. */
    IN_PROGRESS
}
