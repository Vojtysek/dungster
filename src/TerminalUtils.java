import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class TerminalUtils {

    static Scanner scanner = new Scanner(System.in);

    public static void handlePlayerInput(GameMap gameMap) throws IOException, InterruptedException {
        clearScreen();
        Room actualRoom = Main.player.getCurrentRoom();
        Story current = actualRoom.getCurrentDialogue();

        if (actualRoom.isDone()) {
            System.out.println("Tato místnost je již hotová. Můžete pokračovat v průzkumu jiných místností zkrze mapu.");

            typeWriter("m - Open Menu");
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("m")) {
                displayInGameMenu(gameMap);
            }
            return;
        }

        while (true) {
            typeWriter(current.getLine());

            List<Story> options = current.getChoices();

            if (options.isEmpty()) {
                if (current.getFollowUp() != null) {
                    current = current.getFollowUp();
                    actualRoom.setCurrentDialogue(current);
                    Thread.sleep(1000);
                    clearScreen();
                    continue;
                } else {
                    break;
                }
            }

            for (int i = 0; i < options.size(); i++) {
                typeWriter((i + 1) + ". " + options.get(i).getLine());
            }

            typeWriter("m - Open Menu");
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("m")) {
                displayInGameMenu(gameMap);
                return;
            }
            try {
                int selected = Integer.parseInt(input);
                if (selected < 1 || selected > options.size()) {
                    invalidChoice();
                } else {
                    current = options.get(selected - 1).getFollowUp();
                    actualRoom.setCurrentDialogue(current);
                }
            } catch (NumberFormatException e) {
                invalidChoice();
            }
            clearScreen();
        }
    }

    public static void displayInGameMenu(GameMap gameMap) throws InterruptedException, IOException {
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
                        Main.player.getInventory().openInventory();
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
                        Thread.sleep(1000);
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

    public static void typeWriter(String text) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(Main.dev ? 0 : 55);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
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

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.trim();
    }
}
