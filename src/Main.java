import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static boolean dev = false;
    public static Player player = new Player("Steve");

    public Main() {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Rooms.initializeDialogues();
        GameMap gameMap = new GameMap();
        if (!dev) {
            Intro();
        }


        while(true) {
            TerminalUtils.handlePlayerInput(gameMap);
        }

        //(new Ascii()).convertToAscii("src/utils/ascii/wizard.png");
    }

    public static void Intro() throws InterruptedException {
        TerminalUtils.typeWriter("Hello " + Main.player.getName() + "! Welcome to The Forgotten Descent!");
        Thread.sleep(2000);
    }
}
