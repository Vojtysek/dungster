import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static utils.Writer.clearScreen;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Player player = new Player(createPlayer());
        Narrator narrator = new Narrator(player);
        Map map = new Map();

        narrator.Intro();
        narrator.ExploreDungeon(player, map);
    }

    private static String createPlayer() throws IOException {
        System.out.print("Please enter your name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String playerName = reader.readLine();
        clearScreen();
        return playerName;
    }
}