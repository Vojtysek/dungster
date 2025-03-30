import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Terminal {

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

    public static void InvalidChoice() throws InterruptedException {
        clearScreen();
        System.out.println("Invalid choice. Please select a valid option.");
        Thread.sleep(1250);
    }

    public static void waitForInteraction() throws IOException {
        new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    public static void printEmpty() {
        System.out.println();
    }

    public static void displayInGameMenu(Player player, Map map) throws InterruptedException, IOException {
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
                        Terminal.InvalidChoice();
                        break;
                }
            } catch (NumberFormatException e) {
                Terminal.InvalidChoice();
            }
        }
    }
}
