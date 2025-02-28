package dungster;

import utils.Ascii;
import utils.Terminal;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.Thread.sleep;
import static utils.Terminal.InvalidChoice;
import static utils.Terminal.typeWriter;

public class Narrator {

    private static Player player;
    private static Map map;
    private final Ascii ascii = new Ascii();

    public Narrator(Player player, Map map) {
        Narrator.player = player;
        Narrator.map = map;
    }

    public void Intro() throws InterruptedException {
        typeWriter("Hello " + player.getName() + "! Welcome to The Forgotten Descent!", 1000);
    }

    public static void displayInGameMenu() throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            Terminal.clearScreen();
            System.out.println("\n===== IN-GAME MENU =====");
            System.out.println("1. Open Inventory");
            System.out.println("2. Open Map");
            System.out.println("3. Back to game");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        player.getInventory().openInventory();
                        break;
                    case 2:
                        Terminal.clearScreen();
                        map.displayMap();
                        break;
                    case 3:
                        Terminal.clearScreen();
                        return;
                    case 4:
                        Terminal.clearScreen();
                        System.out.println("Exiting game...");
                        sleep(1000);
                        System.exit(0);
                        break;
                    default:
                        InvalidChoice();
                        break;
                }
            } catch (NumberFormatException e) {
                InvalidChoice();
            }
        }
    }

    public void PrisonCell() throws InterruptedException, IOException {
        Terminal.clearScreen();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (player.getInventory().hasItem(Items.ancientKey)) {
                System.out.println("1. Try to open the door");
                System.out.println("(Press 'm' to open the in-game menu at any time)");

                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("m")) {
                    displayInGameMenu();
                } else {
                    if (input.equals("1")) {
                        typeWriter("\nYou use the old key to unlock the door...", 1000);
                        typeWriter("\nYou are now free from the prison cell!", 1000);
                        sleep(1000);
                        player.getInventory().removeItem(Items.ancientKey);
                        player.setCurrentRoom(Rooms.Where);
                        WhereNow();
                    } else {
                        InvalidChoice();
                        sleep(1000);
                        PrisonCell();
                    }
                }

            } else {
                typeWriter("\nYou are in the first chamber. What will you do?", 1000);
                System.out.println("1. Search the room");
                System.out.println("2. Try to open the door");
                System.out.println("(Press 'm' to open the in-game menu at any time)");

                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("m")) {
                    displayInGameMenu();
                } else {
                    switch (input) {
                        case "1":
                            typeWriter("\nYou search the room and find an old key...", 1000);
                            typeWriter("\nYou pick up an old metal key.", 1000);
                            typeWriter("\n*This has to open something*", 1000);
                            player.getInventory().addItem(Items.ancientKey);
                            break;
                        case "2":
                            typeWriter("\nThe door is locked. It seems to require a special key...", 1000);
                            typeWriter("\nYou must find a way to unlock it.", 1000);
                            break;
                        default:
                            InvalidChoice();
                            sleep(1000);
                            PrisonCell();
                            break;
                    }
                }
            }

        }
    }

    public void WhereNow() throws IOException, InterruptedException {
        Terminal.clearScreen();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!player.getInventory().hasItem(Items.alchemistsElixir)) {
                System.out.println("1. Drink Elixir");
                System.out.println("(Press 'm' to open the in-game menu at any time)");
            } else {
                if (!Main.dev) {
                    typeWriter("\nThe door suddenly closes behind you...", 1000);
                    typeWriter("\nYou are now in a dark room. You can see a shape in the distance...", 1000);
                    typeWriter("\nYou approach the shape and try to count how many corners it has...", 1000);
                }
                ascii.convertToAscii("src/utils/ascii/shape.png");
                System.out.println("How many corners does the shape have?");
                System.out.println("(Press 'm' to open the in-game menu at any time)");

                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("m")) {
                    displayInGameMenu();
                } else {
                    if (input.equals("7")) {
                        if (!Main.dev) {
                            typeWriter("\nYou are correct! The shape has 7 corners!", 1000);
                            typeWriter("\nYou scream the nubmber 7 and the shape disappears...", 1000);
                            typeWriter("\nSuddenly, a door appears in front of you...", 1000);
                            typeWriter("\nYou open the door and find a small vent...", 1000);
                        }
                        typeWriter("\nYou crawl through the vent and find yourself in a new room...", 1000);
                        sleep(1000);
                    } else {
                        typeWriter("\nYou are incorrect!", 1000);
                        sleep(1000);
                        WhereNow();
                    }
                }
            }
        }
    }
}
