import utils.Terminal;

import java.io.IOException;
import java.util.ArrayList;

public class Inventory {
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
        currentRoom.addDropedItem(item);
        removeItem(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void openInventory() throws IOException, InterruptedException {
        Terminal.clearScreen();
        if (isEmpty()) {
            System.out.println("Inventory is empty.");
            Thread.sleep(1000);
            Terminal.clearScreen();
            return;
        }
        System.out.println("Inventory:");
        for (Item item : items) {
            if (item != null) {
                item.displayItem();
            }
        }
    }
}
