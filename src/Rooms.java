import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rooms {

    // Up - 0, Right - 2, Down - 1, Left - 3

    public static Room Chamber = new Room("Probuzení", 0, 1, 0, Arrays.asList(1, 2), true);
    public static Room Puppets = new Room("Sklad Loutek", 1, 3, 0, Arrays.asList(1, 3), Main.dev);
    public static Room Voices = new Room("Šeptající hlasy", 2, 3, 1, Arrays.asList(0, 2, 3), Main.dev);
    public static Room Mirrors = new Room("Síň Zrcadel", 3, 1, 1, Arrays.asList(0, 2, 3), Main.dev);
    public static Room Mecha = new Room("Mechanická dílna", 4, 0, 1, Arrays.asList(1, 2), Main.dev);
    public static Room Knots = new Room("Komora uzlů", 5, 0, 2, Arrays.asList(0, 2), Main.dev);
    public static Room Lies = new Room("Archiv lží", 6, 4, 1, Arrays.asList(1, 3), Main.dev);
    public static Room Choices = new Room("Místnost voleb", 7, 4, 2, Arrays.asList(0, 3), Main.dev);
    public static Room Doomsday = new Room("Dveře soudného dne", 8, 2, 2, Arrays.asList(1, 2, 3), Main.dev);
    public static Room Fate = new Room("Zrcadlo Osud", 9, 2, 3, List.of(0), Main.dev);

    public static ArrayList<Room> getRooms() {
        return new ArrayList<>(Arrays.asList(Chamber, Puppets, Voices, Mirrors, Mecha, Knots, Lies, Choices, Doomsday, Fate));
    }

    public static void initializeDialogues() throws IOException {
        Chamber.setDialogueRoot(Stories.ChamberDialogue());
        Puppets.setDialogueRoot(Stories.PuppetsDialogue());
        Voices.setDialogueRoot(Stories.WhispersDialogue());
        Mirrors.setDialogueRoot(Stories.MirrorsDialogue());
        Lies.setDialogueRoot(Stories.LiesDialogue());
        Mecha.setDialogueRoot(Stories.MechaDialogue());
        Knots.setDialogueRoot(Stories.KnotsDialogue());
        Choices.setDialogueRoot(Stories.ChoicesDialogue());
        Doomsday.setDialogueRoot(Stories.DoomsdayDialogue());
        Fate.setDialogueRoot(Stories.FateDialogue());
    }
}