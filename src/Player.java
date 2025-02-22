public class Player {
    private final String name;
    private final Inventory inventory = new Inventory();
    private final Room[]  knownRooms = new Room[0];

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Room[] getKnownRooms() {
        return knownRooms;
    }
}
