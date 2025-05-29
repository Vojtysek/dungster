import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player = new Player("Robert");

    @Test
    void setCurrentRoom() {
        player.setCurrentRoom(Rooms.Mecha);
        assertEquals(Rooms.Mecha, player.getCurrentRoom());
    }
}