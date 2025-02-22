import java.io.IOException;
import java.util.Scanner;

import static utils.Writer.typeWriter;

public class Narrator {

    private final Player player;

    public Narrator(Player player) {
        this.player = player;
    }

    public void Intro() throws InterruptedException {
        typeWriter("Hello " + player.getName() + "! Welcome to The Forgotten Descent!", 1000);
//        typeWriter("\nYou wake up in a dimly lit underground chamber...", 1000);
//        typeWriter("\nThe air is thick with the scent of old parchment and decay.", 1000);
//        typeWriter("\nScattered pages hint at an ancient alchemical experiment gone wrong...", 1000);
//        typeWriter("\nA heavy door blocks the way forward. You must find a way to open it.", 1000);
//        typeWriter("\nYour adventure begins now.\n", 2000);
    }

    public void ExploreDungeon(Player player, Map map) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (player.getInventory().getSlots()[0].getItem() != null) {
                System.out.println("1. Try to open the door");
                System.out.println("(Press 'm' to open the in-game menu at any time)");

                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("m")) {
                    displayInGameMenu(player, map);
                } else {
                    switch (input) {
                        case "1":
                            typeWriter("\nThe door is locked. It seems to require a special key...", 1000);
                            typeWriter("\nYou must find a way to unlock it.", 1000);
                            break;
                        default:
                            typeWriter("\nInvalid choice. Please try again.", 500);
                            ExploreDungeon(player, map);
                            break;
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
                            typeWriter("\nInvalid choice. Please try again.", 500);
                            ExploreDungeon(player, map);
                            break;
                    }
                }
            }
        }
    }

    public void AncientKey() throws InterruptedException {
        typeWriter("\nYou pick up an old metal key.", 1000);
        typeWriter("\n*This has to open something*", 1000);
        player.getInventory().assignItemToSlot(Items.ancientKey);
    }

    public void SolveSymbolPuzzle() throws InterruptedException {
        typeWriter("\nThe symbols on the wall seem to shift as you approach...", 1000);
        typeWriter("\nYou must arrange them in the correct order to proceed.", 1000);
    }

    public void FinalChoice() throws InterruptedException {
        typeWriter("\nYou finally reach the heart of the dungeon.", 1000);
        typeWriter("\nBefore you lies the Key of Oblivion...", 1000);
        typeWriter("\nBut something feels off. You sense a presence watching you...", 1000);

        System.out.println("1. Take the Key and escape");
        System.out.println("2. Use the Key to release the lost souls");
        System.out.println("3. Accept the whispering voice's offer");

    }

    public static void displayInGameMenu(Player player, Map map) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            utils.Writer.clearScreen();
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
                        utils.Writer.clearScreen();
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
