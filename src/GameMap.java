    import java.io.IOException;
    import java.io.Serializable;
    import java.util.ArrayList;

    public class GameMap implements Serializable {
        //for some reason, the 4 is vertical and 5 is horizontal, but I don't have time to deal with this
        // 4 = vertical, 5 = horizontal
        private final String[][] grid = new String[4][5];

        ArrayList<Room> rooms = Rooms.getRooms();
        ArrayList<Tunnel> tunnels = Tunnels.getTunnels();

        String mapaAscii = """
                             .----------------.  .----------------.  .----------------.  .----------------.\s
                            | .--------------. || .--------------. || .--------------. || .--------------. |
                            | | ____    ____ | || |      __      | || |   ______     | || |      __      | |
                            | ||_   \\  /   _|| || |     /  \\     | || |  |_   __ \\   | || |     /  \\     | |
                            | |  |   \\/   |  | || |    / /\\ \\    | || |    | |__) |  | || |    / /\\ \\    | |
                            | |  | |\\  /| |  | || |   / ____ \\   | || |    |  ___/   | || |   / ____ \\   | |
                            | | _| |_\\/_| |_ | || | _/ /    \\ \\_ | || |   _| |_      | || | _/ /    \\ \\_ | |
                            | ||_____||_____|| || ||____|  |____|| || |  |_____|     | || ||____|  |____|| |
                            | |              | || |              | || |              | || |              | |
                            | '--------------' || '--------------' || '--------------' || '--------------' |
                             '----------------'  '----------------'  '----------------'  '----------------'\s
                """;

        public GameMap() {
        }

        public void displayMap() throws IOException, InterruptedException {
            for (Room room : rooms) {
                if (room.isVisible()) {
                    grid[room.getY()][room.getX()] = room.getName();
                }
            }

            for (Tunnel tunnel : tunnels) {
                Room from = tunnel.getFrom();
                Room to = tunnel.getTo();

                if (from.isVisible() && to.isVisible()) {
                    drawTunnel(from.getX(), from.getY(), to.getX(), to.getY());
                }
            }

            while (true) {

                TerminalUtils.clearScreen();

                System.out.println(mapaAscii);

                int contentWidth = 18;

                for (String[] row : grid) {
                    for (String cell : row) {
                        if (cell != null && findRoom(cell) != null) {
                            Room r = findRoom(cell);
                            System.out.print("┌");
                            for (int i = 0; i < contentWidth; i++) {
                                System.out.print((i == (contentWidth / 2) && r.hasDoor(0)) ? " " : "─");
                            }
                            System.out.print("┐ ");
                        } else {
                            System.out.printf("%" + (contentWidth + 3) + "s", " ");
                        }
                    }
                    TerminalUtils.printEmpty();

                    for (int line = 0; line < 5; line++) {
                        for (String cell : row) {
                            if (line == 0) {
                                if (cell != null && findRoom(cell) != null) {
                                    Room r = findRoom(cell);
                                    System.out.print("│");
                                    System.out.print(r.getIndex());
                                    System.out.print(" ".repeat(contentWidth - 1));
                                    System.out.print("│ ");
                                } else {
                                    System.out.printf("%" + (contentWidth + 3) + "s", " ");
                                }
                            } else {
                                printCell(cell, line, contentWidth);
                            }

                        }
                        TerminalUtils.printEmpty();
                    }

                    for (String cell : row) {
                        if (cell != null && findRoom(cell) != null) {
                            Room r = findRoom(cell);
                            System.out.print("└");
                            for (int i = 0; i < contentWidth; i++) {
                                System.out.print((i == (contentWidth / 2) && r.hasDoor(1)) ? " " : "─");
                            }
                            System.out.print("┘ ");
                        } else {
                            System.out.printf("%" + (contentWidth + 3) + "s", " ");
                        }
                    }
                    TerminalUtils.printEmpty();
                }

                System.out.println("Legend:");
                System.out.println("\u001B[41;30m" + " X " + "\u001B[0m - Aktuální pozice");
                System.out.println("Změna místnosti - číslo místnosti");
                System.out.println("m - Menu");

                System.out.print("> ");
                String input = TerminalUtils.getInput();

                if (input.equalsIgnoreCase("m")) {
                    TerminalUtils.displayInGameMenu(this);
                    break;
                } else {
                    try {
                        int selected = Integer.parseInt(input);
                        if (selected >= 0 && selected < rooms.size()) {
                            Room r = findRoom(selected);
                            if (r != null && r.isVisible()) {
                                Main.player.setCurrentRoom(r);
                            } else {
                                TerminalUtils.invalidChoice();
                            }
                        }

                    } catch (NumberFormatException e) {
                        TerminalUtils.invalidChoice();
                    }
                }
            }

        }

        public void drawTunnel(int x1, int y1, int x2, int y2) {
            int dx = Integer.compare(x2, x1);
            int dy = Integer.compare(y2, y1);

            int x = x1 + dx;
            int y = y1 + dy;

            while (x != x2 || y != y2) {
                if (grid[y][x] == null && dx != 0 && dy == 0) {
                    grid[y][x] = "─";
                }

                if (x != x2) x += dx;
                if (y != y2) y += dy;
            }
        }

        private void printCell(String cell, int line, int contentWidth) {
            if (cell == null) {
                System.out.printf("%" + (contentWidth + 3) + "s", " ");
                return;
            }

            if (cell.equals("─")) {
                if (line == 1 || line == 3) {
                    System.out.print(cell);
                    System.out.print("─".repeat(contentWidth));
                    System.out.print(cell);
                } else {
                    System.out.print(" ".repeat(contentWidth + 2));
                }
                System.out.print(" ");
                return;
            }


            Room r = findRoom(cell);
            assert r != null;
            int len = cell.length();
            int leftPadding = ((contentWidth - len) / 2);

            System.out.print((r.hasDoor(3) && line == 2) ? " " : "│");

            if (line == 0) {
                System.out.print(r.getIndex());
            }

            if (line == 2) {
                System.out.print(" ".repeat(leftPadding));
                String paddedName = cell;
                if (Main.player.getCurrentRoom() != null && r == Main.player.getCurrentRoom()) {
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

        public Room findRoom(int index) {
            if (index < 0 || index >= rooms.size()) {
                return null;
            }
            return rooms.get(index);
        }


        public ArrayList<Room> getRooms() {
            return this.rooms;
        }

        public void setRooms(ArrayList<Room> rooms) {
            this.rooms = rooms;
        }

        public ArrayList<Tunnel> getTunnels() {
            return this.tunnels;
        }

        public void setTunnels(ArrayList<Tunnel> tunnels) {
            this.tunnels = tunnels;
        }


    }
