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
    private Story dialogueRoot;
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
        this.dialogueRoot = root;
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
        visible = true;
    }

    public boolean hasDoor(int direction) {
        return doors.contains(direction);
    }

    public void placeItemsRandomly() {

        if (this.items == null) {
            return;
        }

        itemPositions.clear();

        int padding = 2;
        List<int[]> occupiedZones = new ArrayList<>();

        for (Item item : items) {
            int itemWidth = item.getItemName().length();
            int maxX = width - itemWidth - padding;
            int maxY = height - padding - 1;

            boolean placed = false;
            int attempts = 0;

            while (!placed && attempts < 1000) {
                int x = padding + (int) (Math.random() * (maxX - padding + 1));
                int y = padding + (int) (Math.random() * (maxY - padding + 1));
                int x2 = x + itemWidth;
                int y2 = y + 1;

                boolean overlaps = false;
                for (int[] zone : occupiedZones) {
                    int zx1 = zone[0], zy1 = zone[1], zx2 = zone[2], zy2 = zone[3];
                    if (x < zx2 && x2 > zx1 && y < zy2 && y2 > zy1) {
                        overlaps = true;
                        break;
                    }
                }

                if (!overlaps) {
                    int[][] pos = new int[2][2];
                    pos[0][0] = x;
                    pos[0][1] = y;
                    pos[1][0] = x2;
                    pos[1][1] = y2;

                    itemPositions.put(item, pos);
                    occupiedZones.add(new int[]{x, y, x2, y2});
                    placed = true;
                }

                attempts++;
            }
        }
    }


    public void displayRoom() {
        int doorWidth = 10;
        int doorHeight = 5;

        System.out.println();

        int doorTopStart = width / 2 - doorWidth / 2;
        int doorBottomStart = width / 2 - doorWidth / 2;
        int doorLeftStart = height / 2 - doorHeight / 2;
        int doorRightStart = height / 2 - doorHeight / 2;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {

                boolean isTop = row == 0;
                boolean isBottom = row == height - 1;
                boolean isLeft = col == 0;
                boolean isRight = col == width - 1;

                boolean topHasDoor = hasDoor(0);
                boolean bottomHasDoor = hasDoor(1);
                boolean rightHasDoor = hasDoor(2);
                boolean leftHasDoor = hasDoor(3);


                boolean itemDrawn = false;
                for (Item item : itemPositions.keySet()) {
                    int[][] pos = itemPositions.get(item);
                    int startX = pos[0][0];
                    int startY = pos[0][1];
                    int endX = pos[1][0];

                    if (row == startY && col >= startX && col < endX) {
                        System.out.print(item.getItemName().charAt(col - startX));
                        itemDrawn = true;
                        break;
                    }
                }
                if (itemDrawn) continue;

                if ((isTop && isLeft)) {
                    System.out.print("┌");
                } else if ((isTop && isRight)) {
                    System.out.print("┐");
                } else if ((isBottom && isLeft)) {
                    System.out.print("└");
                } else if ((isBottom && isRight)) {
                    System.out.print("┘");
                } else if ((isBottom && bottomHasDoor && col >= doorBottomStart && col < doorBottomStart + doorWidth) || (isTop && topHasDoor && col >= doorTopStart && col < doorTopStart + doorWidth) || (isLeft && leftHasDoor && row >= doorLeftStart && row < doorLeftStart + doorHeight) || (isRight && rightHasDoor && row >= doorRightStart && row < doorRightStart + doorHeight)) {
                    System.out.print(" ");
                } else if (isTop || isBottom) {
                    System.out.print("─");
                } else if (isLeft || isRight) {
                    System.out.print("│");
                } else {
                    System.out.print(" ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }
}