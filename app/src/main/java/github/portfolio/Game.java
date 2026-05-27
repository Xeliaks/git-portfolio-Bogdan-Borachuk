package github.portfolio;

import java.util.Arrays;
import java.util.Objects;

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

    /**
     * Returns the winner's symbol if someone has three in a row, column, or diagonal.
     * Returns null if there is no winner yet.
     */
    public String checkWinner() {
        String[][] cells = board.getCells();

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (cells[i][0] != null
                    && cells[i][0].equals(cells[i][1])
                    && cells[i][1].equals(cells[i][2])) {
                return cells[i][0];
            }
            if (cells[0][i] != null
                    && cells[0][i].equals(cells[1][i])
                    && cells[1][i].equals(cells[2][i])) {
                return cells[0][i];
            }
        }

        // Check diagonals
        if (cells[0][0] != null
                && cells[0][0].equals(cells[1][1])
                && cells[1][1].equals(cells[2][2])) {
            return cells[0][0];
        }
        if (cells[0][2] != null
                && cells[0][2].equals(cells[1][1])
                && cells[1][1].equals(cells[2][0])) {
            return cells[0][2];
        }

        return null;
    }

    /**
     * Returns true when every cell is occupied and there is no winner.
     * Uses a stream to check that no null cell remains on the board.
     */
    public boolean isDraw() {
        if (checkWinner() != null) return false;
        return Arrays.stream(board.getCells())
                .flatMap(Arrays::stream)
                .allMatch(Objects::nonNull);
    }

    /** Convenience method: returns the overall game state. */
    public GameResult getResult() {
        if (checkWinner() != null) return GameResult.WIN;
        if (isDraw()) return GameResult.DRAW;
        return GameResult.IN_PROGRESS;
    }
}
