public class Chest {
    private final Item item;
    private final boolean canOpen;
    private final boolean opened;

    public Chest(Item item, boolean canOpen) {
        this.item = item;
        this.canOpen = canOpen;
        this.opened = false;
    }

    public Item getItem() {
        return item;
    }

    public boolean canOpen() {
        return canOpen;
    }

    public boolean isOpened() {
        return opened;
    }
}
