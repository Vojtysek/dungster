import java.io.Serializable;

public class Player implements Serializable {
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

        switch (room.getId()) {
            case "chamber" -> room.setCurrentDialogue(Rooms.Chamber.getCurrentDialogue());
            case "puppets" -> room.setCurrentDialogue(Rooms.Puppets.getCurrentDialogue());
            case "voices" -> room.setCurrentDialogue(Rooms.Voices.getCurrentDialogue());
            case "mirrors" -> room.setCurrentDialogue(Rooms.Mirrors.getCurrentDialogue());
            case "mecha" -> room.setCurrentDialogue(Rooms.Mecha.getCurrentDialogue());
            case "knots" -> room.setCurrentDialogue(Rooms.Knots.getCurrentDialogue());
            case "lies" -> room.setCurrentDialogue(Rooms.Lies.getCurrentDialogue());
            case "choices" -> room.setCurrentDialogue(Rooms.Choices.getCurrentDialogue());
            case "doomsday" -> room.setCurrentDialogue(Rooms.Doomsday.getCurrentDialogue());
            case "fate" -> room.setCurrentDialogue(Rooms.Fate.getCurrentDialogue());
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
