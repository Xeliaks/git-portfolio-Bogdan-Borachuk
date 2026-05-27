package github.portfolio;

/**
 * Manages the state and flow of a Tic Tac Toe game.
 * Tracks whose turn it is and evaluates the board after each move.
 */
public class Game {

    private final Board board;
    private final Player playerOne;
    private final Player playerTwo;
    private Player currentPlayer;

    public Game(Player playerOne, Player playerTwo) {
        this.board = new Board();
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Places the current player's symbol on the board and advances the turn.
     * Returns false if the move is invalid (out of bounds or cell occupied).
     */
    public boolean makeMove(int row, int col) {
        if (!board.isValidMove(row, col)) {
            return false;
        }
        board.placeSymbol(row, col, currentPlayer.getSymbol());
        switchTurn();
        return true;
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
    }
}
