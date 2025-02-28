package dungster;

import utils.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Map {
    private final ArrayList<Room> rooms = new ArrayList<>() {{
        add(Rooms.Cell);
        add(Rooms.Where);
        add(Rooms.C);
        add(Rooms.D);
        add(Rooms.NotEnd);
        add(Rooms.F);
        add(Rooms.Hidden);
        add(Rooms.H);
        add(Rooms.I);
        add(Rooms.FinalStage);
    }};

    private static Player player;
    private final String[][] grid = new String[3][6];

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

        int contentWidth = 18;

        for (String[] row : grid) {
            for (String cell : row) {
                if (cell != null) {
                    Room r = findRoom(cell);
                    System.out.print("┌");
                    for (int i = 0; i < contentWidth; i++) {
                        System.out.print((i == (contentWidth / 2) && Objects.requireNonNull(r).hasDoor(0)) ? " " : "─");
                    }
                    System.out.print("┐ ");
                } else {
                    System.out.printf("%" + (contentWidth + 3) + "s", " ");
                }
            }
            Terminal.printEmpty();

            for (int line = 0; line < 5; line++) {
                for (String cell : row) {
                    printCell(cell, line, contentWidth);
                }
                Terminal.printEmpty();
            }

            for (String cell : row) {
                if (cell != null) {
                    Room r = findRoom(cell);
                    System.out.print("└");
                    for (int i = 0; i < contentWidth; i++) {
                        System.out.print((i == (contentWidth / 2) && Objects.requireNonNull(r).hasDoor(1)) ? " " : "─");
                    }
                    System.out.print("┘ ");
                } else {
                    System.out.printf("%" + (contentWidth + 3) + "s", " ");
                }
            }
            Terminal.printEmpty();
        }

        System.out.println("Legend:");
        System.out.println("\u001B[41;30m" + " X " + "\u001B[0m - Aktuální pozice");

        Terminal.waitForInteraction();
        Terminal.clearScreen();
    }

    private void printCell(String cell, int line, int contentWidth) {
        if (cell == null) {
            System.out.printf("%" + (contentWidth + 3) + "s", " ");
            return;
        }

        Room r = findRoom(cell);
        assert r != null;
        int len = cell.length();
        int leftPadding = ((contentWidth - len) / 2);

        System.out.print((r.hasDoor(3) && line == 2) ? " " : "│");

        if (line == 2) {
            System.out.print(" ".repeat(leftPadding));
            String paddedName = cell;
            if (player.getCurrentRoom() != null && r == player.getCurrentRoom()) {
                paddedName = "\u001B[41;30m" + paddedName + "\u001B[0m";
            }
            System.out.print(paddedName);
            System.out.print(" ".repeat(contentWidth - len - leftPadding));
        } else {
            System.out.print(" ".repeat(contentWidth));
        }

        System.out.print((r.hasDoor(2) && line == 2) ? " " : "│");
        System.out.print(" ");
    }


    public Room findRoom(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }
}
