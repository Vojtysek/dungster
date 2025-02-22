import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Map {
    private final Room[] rooms = new Room[10];
    private Room currentRoom;

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private final String[][] grid = new String[5][3];

    public Map() {

//        0 = North (Up)
//        1 = South (Down)
//        2 = East (Right)
//        3 = West (Left)

        rooms[0] = new Room("A", 0, 0, List.of(2), true, new Chest(Items.rustyKey, true));
        rooms[1] = new Room("B", 1, 0, Arrays.asList(1, 2, 3), true, null);
        rooms[2] = new Room("C", 2, 0, Arrays.asList(2, 3), false, null);
        rooms[3] = new Room("D", 3, 0, Arrays.asList(1, 3), false, null);
        rooms[4] = new Room("E", 3, 1, Arrays.asList(0, 1), false, null);
        rooms[5] = new Room("F", 3, 2, Arrays.asList(0, 2, 3), false, null);
        rooms[6] = new Room("G", 1, 1, Arrays.asList(0, 1), false, null);
        rooms[7] = new Room("H", 1, 2, Arrays.asList(0, 2), false, null);
        rooms[8] = new Room("I", 2, 2, Arrays.asList(2, 3), false, null);
        rooms[9] = new Room("J", 4, 2, List.of(3), false, null);

    }

    public void displayMap() throws IOException {

        // Naplníme mřížku jen jmény místností, které jsou viditelné
        for (Room room : rooms) {
            if (room.isVisible()) {
                grid[room.getY()][room.getX()] = room.getName();
            }
        }

        System.out.println("Mapa:");

        for (String[] row : grid) {
            // Horní okraj každé buňky
            for (String cell : row) {
                if (cell != null) {
                    Room r = findRoom(cell);
                    System.out.print("┌");
                    for (int i = 0; i < 11; i++) {
                        System.out.print((i == 5 && Objects.requireNonNull(r).hasDoor(0)) ? " " : "─");
                    }
                    System.out.print("┐ ");
                } else {
                    System.out.print("              ");
                }
            }
            printEmpty();

            // Střední část buňky (3 řádky)
            for (int line = 0; line < 3; line++) {
                for (String cell : row) {
                    if (cell != null) {
                        Room r = findRoom(cell);
                        assert r != null;
                        // Levý okraj s možným otvorem na západ (door index 3)
                        System.out.print((r.hasDoor(3) && line == 1) ? " " : "│");

                        // U prostředního řádku zobrazíme název místnosti + indikátor chest, pokud bedna existuje
                        if (line == 1) {
                            String displayName = " " + cell;
                            if (r.getChest() != null) {  // Předpokládáme, že Room má metodu getChest()
                                displayName += " C";
                            }
                            System.out.print(String.format("%-11s", displayName));
                        } else {
                            System.out.print("           ");
                        }

                        // Pravý okraj s možným otvorem na východ (door index 2)
                        System.out.print((r.hasDoor(2) && line == 1) ? " " : "│");
                        System.out.print(" ");
                    } else {
                        System.out.print("              ");
                    }
                }
                printEmpty();
            }

            // Spodní okraj každé buňky
            for (String cell : row) {
                if (cell != null) {
                    Room r = findRoom(cell);
                    System.out.print("└");
                    for (int i = 0; i < 11; i++) {
                        System.out.print((i == 5 && Objects.requireNonNull(r).hasDoor(1)) ? " " : "─");
                    }
                    System.out.print("┘ ");
                } else {
                    System.out.print("              ");
                }
            }
            printEmpty();
        }

        reader.readLine();
        utils.Writer.clearScreen();
    }


    private Room findRoom(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    void printEmpty() {
        System.out.println();
    }

    public void selectRoom(Room room) {
        currentRoom = room;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
