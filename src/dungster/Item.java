package dungster;

import utils.Terminal;

import java.io.IOException;

public class Item {
    private final String itemName;
    private final String itemType;
    private final String itemDescription;

    public Item(String itemName, String itemType, String itemDescription) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemDescription = itemDescription;
    }


    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemType() {
        return itemType;
    }

    public void displayItem() throws IOException {
        System.out.println("┌──────────────────────┐");
        System.out.print("│ ");
        System.out.printf("%-20s", " " + getItemName());
        System.out.println(" │");
        System.out.println("└──────────────────────┘");
    }
}
