package dungster;

public class Player {
    private final String name;
    private final Inventory inventory;
    private Room currentRoom = Rooms.Cell;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory(getCurrentRoom());
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
