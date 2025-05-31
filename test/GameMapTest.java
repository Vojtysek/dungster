import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {

    GameMap gameMap = new GameMap();

    @Test
    void testFindRoomByName() {
        Room found = gameMap.findRoom("Probuzení");
        assertNotNull(found);
        assertEquals(Rooms.Chamber.toString(), found.toString());
    }

    @Test
    void testFindRoomByIndex() {
        Room found = gameMap.findRoom(2);
        assertNotNull(found);
        assertEquals(Rooms.Mirrors.toString(), found.toString());
    }

    @Test
    void testFindRoomByIndexOutOfBounds() {
        Room found = gameMap.findRoom(10);
        assertNull(found);
    }

    @Test
    void testDrawTunnelAddsLineToGrid() {
        gameMap = new GameMap();
        gameMap.drawTunnel(0, 0, 2, 0);
    }
}
