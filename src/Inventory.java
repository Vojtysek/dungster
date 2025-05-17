import java.io.IOException;
import java.util.ArrayList;

public class Inventory {
    //    private final ArrayList<Item> items = Main.dev ? new ArrayList<>() {{
//        add(Items.ancientKey);
//    }} : new ArrayList<>();
    private final ArrayList<Item> items = new ArrayList<>();
    private final Room currentRoom;

    public Inventory(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean hasItem(Item item) {
        for (Item i : items) {
            if (i == item) {
                return true;
            }
        }
        return false;
    }

    public void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void dropItem(Item item) {
        currentRoom.addItem(item);
        removeItem(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void openInventory() {
        TerminalUtils.clearScreen();

        System.out.println("Inventory:");

        int cols = 5;
        int rows = 2;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print("┌──────────────────────┐ ");
            }
            System.out.println();

            for (int c = 0; c < cols; c++) {
                int index = r * cols + c;
                String itemName = "";
                if (items.isEmpty()) {
                    itemName = "";
                }
                if (index < items.size()) {
                    itemName = items.get(index).getItemName();
                }
                System.out.print("│ ");
                System.out.printf("%-20s", itemName);
                System.out.print(" │ ");
            }
            System.out.println();

            for (int c = 0; c < cols; c++) {
                System.out.print("└──────────────────────┘ ");
            }
            System.out.println();
        }
        System.out.print("Press any key to continue...");

        try {
            TerminalUtils.waitForInteraction();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TerminalUtils.clearScreen();
    }
}
