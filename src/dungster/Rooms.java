package dungster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rooms {

    public static Room Cell = new Room("Cell", 0, 0, List.of(2), true);
    public static Room Where = new Room("Weird Shape?", 1, 0, Arrays.asList(1, 2, 3), Main.dev);
    public static Room Hidden = new Room("Hidden Pathway", 1, 1, Arrays.asList(0, 1), Main.dev);
    public static Room C = new Room("C", 2, 0, Arrays.asList(2, 3), Main.dev);
    public static Room D = new Room("D", 3, 0, Arrays.asList(1, 3), Main.dev);
    public static Room F = new Room("F", 3, 2, Arrays.asList(0, 2, 3), Main.dev);
    public static Room H = new Room("H", 1, 2, Arrays.asList(0, 2), Main.dev);
    public static Room I = new Room("I", 2, 2, Arrays.asList(2, 3), Main.dev);
    public static Room NotEnd = new Room("Is this end?", 3, 1, List.of(0), Main.dev);
    public static Room FinalStage = new Room("Final", 4, 2, List.of(3), Main.dev);

    public static ArrayList<Room> getRooms() {
        return new ArrayList<>(Arrays.asList(Cell, Where, C, D, NotEnd, F, Hidden, H, I, FinalStage));
    }
}
