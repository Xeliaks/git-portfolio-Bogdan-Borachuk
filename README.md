# Tic Tac Toe — A Java Console Game

## Description

A two-player console-based Tic Tac Toe game built with Java 21 and Gradle.
Two players take turns entering a row and column to place their symbol (X or O)
on a 3×3 board. The first player to align three symbols wins. If all cells are
filled with no winner, the game ends in a draw.

## Project Structure

```
app/src/main/java/github/portfolio/
├── App.java           — Entry point, wires the game loop
├── Board.java         — 3×3 grid state, display, and cell validation
├── Game.java          — Turn management, win and draw detection
├── GameResult.java    — Enum: WIN, DRAW, IN_PROGRESS
├── InputHandler.java  — Console input parsing and validation
└── Player.java        — Player name and symbol (X or O)
```

## How to Build and Run

**Prerequisites:** Java 21+, internet connection (first run downloads dependencies)

```bash
# Build the project
./gradlew build

# Run the game
./gradlew run

# Run all tests
./gradlew test
```

The compiled JAR is produced in `app/build/libs/`.

## Build Tool: Gradle

Gradle was chosen over Maven because:
- Concise Groovy DSL — less boilerplate than XML
- Faster incremental builds with a build cache
- The version catalog (`gradle/libs.versions.toml`) centralizes all dependency
  versions in one place, making upgrades easy

## Git Concepts Demonstrated

This repository was built to practice the following Git workflows:

| Concept | How it appears in this repo |
|---------|----------------------------|
| Feature branching | `feature/game-board`, `feature/game-logic`, `feature/player-input`, `feature/unit-tests` |
| Three-way merge | `feature/game-board` merged with `--no-ff` after `main` diverged |
| Merge conflict | `Game.java` conflicted between `main` and `feature/player-input` |
| Meaningful commits | Imperative mood, one logical change per commit |
| Proper .gitignore | Excludes `.gradle/`, `build/`, `*.class` |

### What is a three-way merge?

A fast-forward merge simply moves the branch pointer forward — no real combining happens.
A **three-way merge** occurs when both branches have diverged: Git finds the common ancestor
commit and combines the changes from both sides into a new merge commit. You can see this
in the git log graph as two lines joining into one (`--no-ff` preserves this history).

## Conflict Resolution

**File conflicted:** `app/src/main/java/github/portfolio/Game.java`

**Which method:** `isDraw()`

**Cause:**
- `main` refactored `isDraw()` to use a nested loop to scan for empty cells
- `feature/player-input` refactored the same method to use Java Streams (`Arrays.stream().flatMap().allMatch()`)
- Both changes targeted the same lines — Git could not auto-merge

**How the conflict appeared in the file:**
```
<<<<<<< HEAD
     * Uses a nested loop to scan every cell for a null (empty) slot.
     */
    public boolean isDraw() {
        if (checkWinner() != null) return false;
        for (String[] row : board.getCells()) {
            for (String cell : row) {
                if (cell == null) return false;
            }
        }
        return true;
=======
     * Uses a stream to check that no null cell remains on the board.
     */
    public boolean isDraw() {
        if (checkWinner() != null) return false;
        return Arrays.stream(board.getCells())
                .flatMap(Arrays::stream)
                .allMatch(Objects::nonNull);
>>>>>>> feature/player-input
    }
```

**Resolution:** Kept the loop-based implementation for clarity and readability.
The stream approach is more concise but less immediately readable for developers
unfamiliar with Java Streams. The conflict markers were removed and the file was
staged with `git add`, then the merge was completed with `git commit`.
