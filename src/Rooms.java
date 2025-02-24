import java.util.Arrays;
import java.util.List;

public class Rooms {
    public static Room Cell = new Room("A", 0, 0, List.of(2), true);
    public static Room B = new Room("B", 1, 0, Arrays.asList(1, 2, 3), false);
    public static Room C = new Room("C", 2, 0, Arrays.asList(2, 3), false);
    public static Room D = new Room("D", 3, 0, Arrays.asList(1, 3), false);
    public static Room E = new Room("E", 3, 1, List.of(0), false);
    public static Room F = new Room("F", 3, 2, Arrays.asList(0, 2, 3), false);
    public static Room G = new Room("G", 1, 1, Arrays.asList(0, 1), false);
    public static Room H = new Room("H", 1, 2, Arrays.asList(0, 2), false);
    public static Room I = new Room("I", 2, 2, Arrays.asList(2, 3), false);
    public static Room FinalStage = new Room("J", 4, 2, List.of(3), false);
}
