package dungster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static utils.Terminal.*;

public class Main {

    public static boolean dev = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        RunPub();
    }

    private static void RunPub() throws IOException, InterruptedException {
        Player player = new Player(createPlayer());
        Map map = new Map(player);
        Narrator narrator = new Narrator(player, map);

        if (!dev) narrator.Intro();

        if (player.getCurrentRoom() == Rooms.Cell) {
            narrator.PrisonCell(player, map);
        }
        if (player.getCurrentRoom() == Rooms.B) {
            System.out.println("You are in room B");
            while (true) {
                Narrator.displayInGameMenu(player, map);
            }
        } else {
            System.out.println("You are in void, how tf did you managed that?!");
        }
    }

    private static String createPlayer() throws IOException {
        System.out.print("Please enter your name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String playerName = reader.readLine();
        clearScreen();
        return playerName;
    }
}