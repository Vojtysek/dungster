import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private String name;
    private final int x;
    private final int y;
    private final List<Integer> doors;
    private boolean visible;
    private final int width = 66;
    private final int height = 20;
    private final Map<Item, int[][]> itemPositions = new HashMap<>();
    private ArrayList<Item> items;
    private Story currentDialogue;

    public Room(String name, int x, int y, List<Integer> doors, boolean visible, ArrayList<Item> items) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.doors = doors;
        this.visible = visible;
        this.items = items;
    }

    public Story getCurrentDialogue() {
        return currentDialogue;
    }

    public Room(String name, int x, int y, List<Integer> doors, boolean visible) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.doors = doors;
        this.visible = visible;
    }

    public Room(int x, int y, List<Integer> doors, boolean visible) {
        this.x = x;
        this.y = y;
        this.doors = doors;
        this.visible = visible;
    }

    public void setCurrentDialogue(Story choice) {
        this.currentDialogue = choice;
    }

    public void setDialogueRoot(Story root) {
        this.currentDialogue = root;
    }

    public void addItem(Item item) {
        items.add(item);
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
        this.visible = true;
    }

    public boolean hasDoor(int direction) {
        return doors.contains(direction);
    }
}