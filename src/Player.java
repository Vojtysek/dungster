public class Player {
    private final String name;
    private final Inventory inventory = new Inventory(getCurrentRoom());
    private Room currentRoom = Rooms.Cell;

    public Player(String name) {
        this.name = name;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
