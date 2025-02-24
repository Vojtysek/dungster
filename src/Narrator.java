import utils.Terminal;

import java.io.IOException;
import java.lang.Thread;
import java.util.Scanner;

import static utils.Terminal.typeWriter;

public class Narrator {

    private final Player player;
    
    public Narrator(Player player, Map map) {
        this.player = player;
    }

    public void Intro() throws InterruptedException {
        typeWriter("Hello " + player.getName() + "! Welcome to The Forgotten Descent!", 1000);
    }

    public void PrisonCell(Player player, Map map) throws InterruptedException {
        Terminal.clearScreen();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (player.getInventory().hasItem(Items.ancientKey)) {
                System.out.println("1. Try to open the door");
                System.out.println("(Press 'm' to open the in-game menu at any time)");

                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("m")) {
                    displayInGameMenu(player, map);
                } else {
                    if (input.equals("1")) {
                        typeWriter("\nYou use the ancient key to unlock the door...", 1000);
                        typeWriter("\nYou are now free!", 1000);
                        player.setCurrentRoom(Rooms.B);
                        map.findRoom("B").setVisibility();
                        break;
                    } else {
                        System.out.println("Invalid choice. Please select a valid option.");
                        Thread.sleep(1000);
                        PrisonCell(player, map);
                    }
                }
            } else {
                typeWriter("\nYou are in the first chamber. What will you do?", 1000);
                System.out.println("1. Search the room");
                System.out.println("2. Try to open the door");
                System.out.println("(Press 'm' to open the in-game menu at any time)");

                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("m")) {
                    displayInGameMenu(player, map);
                } else {
                    switch (input) {
                        case "1":
                            typeWriter("\nYou search the room and find an old key...", 1000);
                            AncientKey();
                            break;
                        case "2":
                            typeWriter("\nThe door is locked. It seems to require a special key...", 1000);
                            typeWriter("\nYou must find a way to unlock it.", 1000);
                            break;
                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                            Thread.sleep(1000);
                            PrisonCell(player, map);
                            break;
                    }
                }
            }
        }
    }

    public void AncientKey() throws InterruptedException {
        typeWriter("\nYou pick up an old metal key.", 1000);
        typeWriter("\n*This has to open something*", 1000);
        player.getInventory().addItem(Items.ancientKey);
    }

    public static void displayInGameMenu(Player player, Map map) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            Terminal.clearScreen();
            System.out.println("\n===== IN-GAME MENU =====");
            System.out.println("1. Open Inventory");
            System.out.println("2. Open Map");
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
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            break;
        }
    }


}
