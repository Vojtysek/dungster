import java.util.List;

public class Room {
    private final int x;
    private final int y;
    private final List<Integer> doors;
    private int index;
    private String name;
    private boolean visible;
    private Story currentDialogue;
    private boolean done = false;

    public Room(String name, int index, int x, int y, List<Integer> doors, boolean visible) {
        this.name = name;
        this.index = index;
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
        return this.visible;
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
