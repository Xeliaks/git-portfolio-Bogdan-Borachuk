package github.portfolio;

import java.util.Scanner;

/**
 * Reads and validates player input from the console.
 * Accepts row and column numbers in the range 1–3.
 */
public class InputHandler {

    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prompts the player for a move and returns a validated [row, col] array (0-indexed).
     * Keeps prompting until the input is in range and the cell is empty.
     */
    public int[] promptMove(Player player, Board board) {
        while (true) {
            System.out.print(player.getName() + "'s turn " + player.getSymbol()
                    + " — Enter row and column (e.g. 2 3): ");
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");

            if (parts.length != 2) {
                System.out.println("  Please enter two numbers separated by a space.");
                continue;
            }

            int row, col;
            try {
                row = Integer.parseInt(parts[0]) - 1;
                col = Integer.parseInt(parts[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input — please enter numbers only.");
                continue;
            }

            if (!board.isValidMove(row, col)) {
                System.out.println("  That cell is taken or out of range. Try again.");
                continue;
            }

            return new int[]{row, col};
        }
    }

    /**
     * Prompts for a player name, ensuring it is not blank.
     */
    public String promptName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) return name;
            System.out.println("  Name cannot be empty.");
        }
    }
}
