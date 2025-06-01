import java.io.IOException;

public class Main {
    private static final GameMap gameMap = new GameMap();
    public static boolean dev = false;
    public static Player player = new Player();

    public static void main(String[] args) throws IOException, InterruptedException {

        Rooms.initializeDialogues();
        TerminalUtils.clearScreen();

        while (true) {
            TerminalUtils.handlePlayerInput(gameMap);
        }
    }
}
