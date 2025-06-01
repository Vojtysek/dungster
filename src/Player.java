public class Player {
    private final Inventory inventory = new Inventory();
    private Room currentRoom = Rooms.Chamber;

    public Player() {
        setCurrentRoom(currentRoom);
        inventory.addItem(Items.map);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
        room.setVisibility();
    }

    public Inventory getInventory() {
        return inventory;
    }
}
