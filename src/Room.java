import java.util.List;

public class Room {
    private final String name;
    private final int x;
    private final int y;
    private final List<Integer> doors;
    private boolean visible;
    private Item[] dropedItem;

    public Room(String name, int x, int y, List<Integer> doors, boolean visible) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.doors = doors;
        this.visible = visible;
    }

    public Item[] getDropedItem() {
        return dropedItem;
    }

    public void addDropedItem(Item item) {
        for (int i = 0; i < dropedItem.length; i++) {
            if (dropedItem[i] == null) {
                dropedItem[i] = item;
                break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisibility() {
        visible = true;
    }

    public boolean hasDoor(int direction) {
        return doors.contains(direction);
    }
}
