import java.io.IOException;

public class Main {


    public static boolean dev = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        Terminal.clearScreen();
        Player player = new Player(dev ? "dev" : Narrator.createPlayer());
        Map map = new Map(player);
        Narrator narrator = new Narrator(player, map);

        if (!dev) narrator.Intro();

        if (player.getCurrentRoom() == Rooms.Chamber) {
            narrator.PrisonCell();
        }
        if (player.getCurrentRoom() == Rooms.Puppets) {
            System.out.println("You are in room B");
            while (true) {
                Terminal.displayInGameMenu(player, map);
            }
        } else {
            System.out.println("You are in void, how tf did you managed that?!");
        }

//        Ascii ascii = new Ascii();
//        ascii.convertToAscii("src/utils/ascii/wizard.png");
    }
}