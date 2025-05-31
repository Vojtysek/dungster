import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static boolean dev = false;
    public static Player player;
    private static GameMap gameMap;

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Chceš načíst uloženou hru? (a/n)");
        String input = reader.readLine().trim().toLowerCase();

        File saveFile = new File("save.dat");

        if (input.equals("a") && saveFile.exists()) {
            Object[] loaded = SaveLoadSystem.load();
            Thread.sleep(1500);
            if (loaded != null) {
                player = (Player) loaded[0];
                gameMap = (GameMap) loaded[1];

                Rooms.initializeDialogues(); // nejdřív vygeneruj nové příběhy
                Room current = player.getCurrentRoom();
                player.setCurrentRoom(current); // znovu připojí aktuální Story

            } else {
                System.out.println("❌ Save existuje, ale je poškozen. Spouštím novou hru...");
                player = new Player();
                gameMap = new GameMap();
                Rooms.initializeDialogues();
            }
        } else {
            if (input.equals("a")) {
                System.out.println("❌ Save.dat neexistuje. Spouštím novou hru...");
                Thread.sleep(1500);
            }
            player = new Player();
            gameMap = new GameMap();
            Rooms.initializeDialogues();
        }


        while (true) {
            try {
                TerminalUtils.handlePlayerInput(gameMap);
                SaveLoadSystem.save(player, gameMap);
            } catch (Exception e) {
                if (player.getCurrentRoom() == Rooms.Chamber && player.getCurrentRoom().getCurrentDialogue() == null) {
                    initializeGame();
                } else {
                    throw e;
                }
            }
        }
    }

    private static void initializeGame() throws IOException {
        if (gameMap == null) {
            gameMap = new GameMap();
        }
        TerminalUtils.clearScreen();
    }
}
