import java.util.ArrayList;
import java.util.Arrays;

public class Tunnels {

    public static Tunnel CtP = new Tunnel(2, 0, Arrays.asList(2, 3), Main.dev, Rooms.Chamber, Rooms.Puppets);
    public static Tunnel MtV = new Tunnel(2, 0, Arrays.asList(2, 3), Main.dev, Rooms.Mirrors, Rooms.Voices);
    public static Tunnel KtD = new Tunnel(2, 0, Arrays.asList(2, 3), Main.dev, Rooms.Knots, Rooms.Doomsday);
    public static Tunnel CtD = new Tunnel(2, 0, Arrays.asList(2, 3), Main.dev, Rooms.Choices, Rooms.Doomsday);


    public static ArrayList<Tunnel> getTunnels() {
        return new ArrayList<>(Arrays.asList(CtP, MtV, KtD, CtD));

    }

}
