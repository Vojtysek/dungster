import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;


public class TerminalUtils {

    public static void handlePlayerInput(Player player, GameMap gameMap) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Room actualRoom = player.getCurrentRoom();
        Story current = actualRoom.getCurrentDialogue();

        while (true) {
            actualRoom.displayRoom();

            System.out.println(current.getLine());

            List<Story> options = current.getNextChoices();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i).getLine());
            }

            System.out.println("m - Open Menu");
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("m")) {
                displayInGameMenu(player, gameMap);
                continue;
            }

            try {
                int selected = Integer.parseInt(input);
                if (selected < 1 || selected > options.size()) {
                    invalidChoice();
                } else {
                    current = options.get(selected - 1);
                    actualRoom.setCurrentDialogue(current);
                    if (current.isTerminal()) {
                        clearScreen();
                        return;
                    }
                }
            } catch (NumberFormatException e) {
                invalidChoice();
            }
            clearScreen();
        }
    }
    
    public static void displayInGameMenu(Player player, GameMap gameMap) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            clearScreen();
            System.out.println("===== IN-GAME MENU =====");
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
                        clearScreen();
                        gameMap.displayMap();
                        break;
                    case 3:
                        clearScreen();
                        return;
                    case 4:
                        clearScreen();
                        System.out.println("Exiting game...");
                        sleep(1000);
                        System.exit(0);
                        break;
                    default:
                        invalidChoice();
                        break;
                }
            } catch (NumberFormatException e) {
                invalidChoice();
            }
        }
    }

    public static void typeWriter(String text, int sleepTime) throws InterruptedException {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(Main.dev ? 0 : 20);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        Thread.sleep(sleepTime);
        clearScreen();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void invalidChoice() throws InterruptedException, IOException {
        clearScreen();
        System.out.println("Invalid choice. Please select a valid option.");
        waitForInteraction();
        clearScreen();
    }

    public static void waitForInteraction() throws IOException {
        new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    public static void printEmpty() {
        System.out.println();
    }
}
