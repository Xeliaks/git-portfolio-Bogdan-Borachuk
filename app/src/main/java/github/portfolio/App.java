package github.portfolio;

/**
 * Entry point for the Tic Tac Toe game.
 * Collects player names, then runs the game loop until win or draw.
 */
public class App {

    public static void main(String[] args) {
        InputHandler input = new InputHandler();

        System.out.println("=== Tic Tac Toe ===");
        String name1 = input.promptName("Enter name for Player 1 (X): ");
        String name2 = input.promptName("Enter name for Player 2 (O): ");

        Player p1 = new Player(name1, "X");
        Player p2 = new Player(name2, "O");
        Game game = new Game(p1, p2);

        while (game.getResult() == GameResult.IN_PROGRESS) {
            game.getBoard().display();
            int[] move = input.promptMove(game.getCurrentPlayer(), game.getBoard());
            game.makeMove(move[0], move[1]);
        }

        game.getBoard().display();

        if (game.getResult() == GameResult.DRAW) {
            System.out.println("It's a draw!");
        } else {
            // makeMove() already switched the turn, so we re-check the board for the winner symbol.
            String winnerSymbol = game.checkWinner();
            String winnerName = p1.getSymbol().equals(winnerSymbol) ? p1.getName() : p2.getName();
            System.out.println(winnerName + " wins! Congratulations!");
        }
    }
}
