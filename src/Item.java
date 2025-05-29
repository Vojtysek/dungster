public class Item {
    private final String itemName;
    private final String itemType;
    private final String itemDescription;

    public Item(String itemName, String itemType, String itemDescription) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return itemName;
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
}
