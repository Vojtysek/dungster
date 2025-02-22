import java.util.List;

class Room {
    private final String name;
    private final int x;
    private final int y;
    private final List<Integer> doors;
    private final boolean visible;
    private final Chest chests;

    public Room(String name, int x, int y, List<Integer> doors, boolean visible, Chest chests) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.doors = doors;
        this.visible = visible;
        this.chests = chests;
    }

    public Chest getChest() {
        return chests;
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

    public boolean hasDoor(int direction) {
        return doors.contains(direction);
    }

    public void displayRoom() {
        int width = 20;
        for (int i = 0; i < width; i++) {
            int height = 20;
            for (int j = 0; j < height; j++) {
                System.out.print(doors.get(i) + ", ");
            }
        }
    }
}
