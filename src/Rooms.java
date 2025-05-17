import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rooms {

    // Up - 0, Right - 2, Down - 1, Left - 3

    public static Room Chamber = new Room("Probuzení", 1, 0, Arrays.asList(1, 2), true, new ArrayList<>(Arrays.asList(Items.ancientKey, Items.braceletThreads04)));
    public static Room Puppets = new Room("Sklad Loutek", 3, 0, Arrays.asList(1, 3), Main.dev);
    public static Room Mirrors = new Room("Síň Zrcadel", 1, 1, Arrays.asList(0, 2, 3), Main.dev);
    public static Room Voices = new Room("Šeptající hlasy", 3, 1, Arrays.asList(0, 2, 3), Main.dev);
    public static Room Mecha = new Room("Mechanická dílna", 0, 1, Arrays.asList(1, 2), Main.dev);
    public static Room Knots = new Room("Komora uzlů", 0, 2, Arrays.asList(0, 2), Main.dev);
    public static Room Lies = new Room("Archiv lží", 4, 1, Arrays.asList(1, 3), Main.dev);
    public static Room Choices = new Room("Místnost voleb", 4, 2, Arrays.asList(0, 3), Main.dev);
    public static Room Doomsday = new Room("Dveře soudného dne", 2, 2, Arrays.asList(1, 2, 3), Main.dev);
    public static Room Fate = new Room("Zrcadlo Osud", 2, 3, List.of(0), Main.dev);


    public static ArrayList<Room> getRooms() {
        return new ArrayList<>(Arrays.asList(Chamber, Puppets, Mirrors, Voices, Mecha, Knots, Lies, Choices, Doomsday, Fate));
    }

    public static void initializeDialogues() {
        Chamber.setDialogueRoot(Stories.ChamberDialogue());
    }
}