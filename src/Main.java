import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static boolean dev = true;

    public static void main(String[] args) throws IOException, InterruptedException {

        Rooms.initializeDialogues();
        Player player = new Player(dev ? "dev" : createPlayer());
        GameMap gameMap = new GameMap(player);
        if (!dev) {
            Intro(player);
        }

        while (true) {
            TerminalUtils.handlePlayerInput(player, gameMap);
            TerminalUtils.waitForInteraction();
        }

//        Ascii ascii = new Ascii();
//        ascii.convertToAscii("src/utils/ascii/wizard.png");
    }

    public static String createPlayer() throws IOException {
        System.out.print("Please enter your name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String playerName = reader.readLine();
        TerminalUtils.clearScreen();
        return playerName;
    }

    public static void Intro(Player player) throws InterruptedException {
        TerminalUtils.typeWriter("Hello " + player.getName() + "! Welcome to The Forgotten Descent!", 1000);
    }
}