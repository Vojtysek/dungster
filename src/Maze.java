import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Maze {

    private final String maze = """
            ══════╦═════════╦═════════╦═══╗
                  ║         ║         ║   ║
            ╔═══╗ ╠══════ ║ ║ ══╗ ╔══ ║ ║ ║
            ║   ║ ║       ║ ║   ║ ║   ║ ║ ║
            ║ ║ ║ ║ ════╦═╣ ╚═══╝ ║ ╔═╩═╝ ║
            ║ ║ ║ ║     ║ ║       ║ ║     ║
            ║ ╚═╣ ╚════ ║ ╚═╦═════╝ ╠══ ║ ║
            ║   ║       ║   ║       ║   ║ ║
            ║ ║ ╚═══╦═══╝ ║ ║ ╔═══╦═╝ ╔═╣ ║
            ║ ║     ║     ║ ║ ║   ║   ║ ║ ║
            ║ ╚═╗ ══╝ ════╣ ║ ║ ║ ╚══ ║ ║ ║
            ║   ║         ║     ║     ║   X
            ╚═══╩═════════╩═════╩═════╩════""";

    private final String[] mazeLines = maze.split("\n");
    private final int mazeWidth = mazeLines[0].length();
    private final int mazeHeight = mazeLines.length;
    private final int exitX = 30;
    private final int exitY = 11;
    private int playerX = 0;
    private int playerY = 1;
    private boolean solved = false;

    public void printMazeWithPlayer() {
        System.out.println("\nPohybuj se pomocí WASD (w-nahoru, a-doleva, s-dolů, d-doprava)");
        System.out.println("Najdi cestu ven z bludiště (označeno 'X'):\n");

        for (int y = 0; y < mazeHeight; y++) {
            for (int x = 0; x < mazeWidth; x++) {
                if (x == playerX && y == playerY) {
                    System.out.print("•");
                } else if (x == exitX && y == exitY) {
                    System.out.print("E");
                } else {
                    System.out.print(mazeLines[y].charAt(x));
                }
            }
            System.out.println();
        }
    }

    public boolean movePlayer(char direction) {
        int newX = playerX;
        int newY = playerY;

        switch (Character.toLowerCase(direction)) {
            case 'w': // up
                newY--;
                break;
            case 'd': // right
                newX++;
                break;
            case 's': // down
                newY++;
                break;
            case 'a': // left
                newX--;
                break;
            default:
                return false; // Invalid input
        }

        if (newY >= 0 && newY < mazeHeight && newX >= 0 && newX < mazeWidth) {
            char targetCell = mazeLines[newY].charAt(newX);
            if (targetCell == 'X' || targetCell == ' ') {
                playerX = newX;
                playerY = newY;

                if (playerX == exitX && playerY == exitY) {
                    solved = true;
                }
                return true;
            }
        }
        return false;
    }

    public void resetMaze() {
        playerX = 0;
        playerY = 1;
        solved = false;
    }

    public void playMaze() throws IOException, InterruptedException {
        resetMaze();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!solved) {
            TerminalUtils.clearScreen();
            printMazeWithPlayer();
            System.out.print("\nZadej směr (WASD): ");

            String input = reader.readLine().trim();
            if (input.isEmpty()) continue;

            char move = input.charAt(0);

            boolean moved = movePlayer(move);
            if (!moved) {
                System.out.println("Nemůžeš jít tímto směrem!");
                TerminalUtils.waitForInteraction();
            }
        }

        TerminalUtils.clearScreen();
        printMazeWithPlayer();
        System.out.println("\nGratulujeme! Našel jsi cestu z bludiště!");
        Thread.sleep(500);
        TerminalUtils.clearScreen();
    }

}
