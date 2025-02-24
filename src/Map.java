import utils.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Map {
    private final ArrayList<Room> rooms = new ArrayList<>() {{
        add(Rooms.Cell);
        add(Rooms.B);
        add(Rooms.C);
        add(Rooms.D);
        add(Rooms.E);
        add(Rooms.F);
        add(Rooms.G);
        add(Rooms.H);
        add(Rooms.I);
        add(Rooms.FinalStage);
    }};
    private Room currentRoom;

    private static Player player;
    private final String[][] grid = new String[3][5];

//    X = vertical
//    Y = horizontal

    public Map(Player player) {
        Map.player = player;
    }

    public void displayMap() throws IOException {
        for (Room room : rooms) {
            if (room.isVisible()) {
                grid[room.getY()][room.getX()] = room.getName();
            }
        }

        System.out.println("Mapa:");

        for (String[] row : grid) {
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
            Terminal.printEmpty();

            for (int line = 0; line < 3; line++) {
                for (String cell : row) {
                    if (cell != null) {
                        Room r = findRoom(cell);
                        assert r != null;

                        System.out.print((r.hasDoor(3) && line == 1) ? " " : "│");

                        if (line == 1) {
                            String displayName = " " + cell;
                            System.out.printf("%-11s", displayName);
                        } else {
                            System.out.print("           ");
                        }

                        System.out.print((r.hasDoor(2) && line == 1) ? " " : "│");
                        System.out.print(" ");
                    } else {
                        System.out.print("              ");
                    }
                }
                Terminal.printEmpty();
            }

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
            Terminal.printEmpty();
        }

        Terminal.waitForInteraction();
        Terminal.clearScreen();
    }


    public Room findRoom(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    public void selectRoom(Room room) {
        currentRoom = room;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
