import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room implements Serializable {
    private final String id;
    private final int x;
    private final int y;
    private final List<Integer> doors;
    private final Map<Item, int[][]> itemPositions = new HashMap<>();
    private int index;
    private String name;
    private boolean visible;
    private Story currentDialogue;
    private boolean done = false;

    public Room(String id, String name, int index, int x, int y, List<Integer> doors, boolean visible) {
        this.id = id;
        this.name = name;
        this.index = index;
        this.x = x;
        this.y = y;
        this.doors = doors;
        this.visible = visible;
    }

    public Room(int x, int y, List<Integer> doors, boolean visible) {
        this("undefined", "unnamed", -1, x, y, doors, visible);
    }


    public String getId() {
        return id;
    }

    public Story getCurrentDialogue() {
        return currentDialogue;
    }

    public void setCurrentDialogue(Story dialogue) {
        this.currentDialogue = dialogue;
    }

    public void setDialogueRoot(Story root) {
        this.currentDialogue = root;
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

    public int getIndex() {
        return index;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
