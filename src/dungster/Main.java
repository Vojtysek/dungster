package dungster;

import utils.Ascii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static dungster.Narrator.displayInGameMenu;
import static utils.Terminal.clearScreen;
import static utils.Terminal.waitForInteraction;

public class Main {

    public static boolean dev = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        clearScreen();
//        long start = System.currentTimeMillis();
//        new Ascii().convertToAscii("src/utils/ascii/wizard.png");
//        long end = System.currentTimeMillis();
//        System.out.println("Time taken: " + (end - start) + "ms");
        RunPub();
    }

    private static void RunPub() throws IOException, InterruptedException {

        Player player = new Player(dev ? "dev" : createPlayer());
        Map map = new Map(player);
        Narrator narrator = new Narrator(player, map);

        if (!dev) narrator.Intro();

        if (player.getCurrentRoom() == Rooms.Cell) {
            narrator.PrisonCell();
        }
        if (player.getCurrentRoom() == Rooms.Where) {
            System.out.println("You are in room B");
            while (true) {
                displayInGameMenu();
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