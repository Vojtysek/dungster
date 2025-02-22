import java.io.IOException;

public class Inventory {
    private final int INVENTORY_SIZE = 5;
    private final Slot[] slots = new Slot[INVENTORY_SIZE];

    public Inventory() {
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            slots[i] = new Slot(i);
        }
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void openInventory() throws IOException {
        System.out.println("Inventory:");
        for (Slot slot : slots) {
            if (slot.getItem() != null) {
                slot.displaySlot();
            }
        }
    }

    public void assignItemToSlot(Item item) {
        for (Slot slot : slots) {
            if (slot.getItem() == null) {
                slot.setItem(item);
                break;
            }
        }
    }
}
