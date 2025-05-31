import java.io.*;

public class SaveLoadSystem {

    public static void save(Player p, GameMap m) throws InterruptedException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("save.dat"))) {
            out.writeObject(p);
            out.writeObject(m);
        } catch (IOException e) {
            System.out.println("❌ Ukládání selhalo: " + e.getMessage());
            Thread.sleep(1500);
        }
    }

    public static Object[] load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("save.dat"))) {
            Player loadedPlayer = (Player) in.readObject();
            GameMap loadedMap = (GameMap) in.readObject();
            System.out.println("✔ Hra byla úspěšně načtena.");
            return new Object[]{loadedPlayer, loadedMap};
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Načítání selhalo: " + e.getMessage());
            return null;
        }
    }
}
