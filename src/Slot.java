import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Slot {
    private Item item;
    private final int slotIndex;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Slot(int slotIndex) {
        this.slotIndex = slotIndex;
    }


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void displaySlot() throws IOException {
        utils.Writer.clearScreen();
        System.out.println("┌──────────────────────┐");
        System.out.print("│ ");
        System.out.printf("%-20s", " " + item.getItemName());
        System.out.println(" │");
        System.out.println("└──────────────────────┘");
        reader.readLine();
        utils.Writer.clearScreen();
    }

    public int getSlotIndex() {
        return slotIndex;
    }
}
