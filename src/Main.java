import java.io.IOException;

public class Main {
    public static boolean dev = true;
    public static Player player = new Player("Robert");

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

    }

    public static void Intro() throws InterruptedException {
        TerminalUtils.clearScreen();
        TerminalUtils.typeWriter("Vítej " + Main.player.getName() + "e! Čeká tě dobrodružství v temném světě plném záhad a nebezpečí.");
        Thread.sleep(2000);
    }
}
