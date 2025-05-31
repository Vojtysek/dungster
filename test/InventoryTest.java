import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class InventoryTest {

    Inventory inventory = new Inventory();
    @BeforeEach
    void setUp() {
        inventory.addItem(Items.ancientKey);
    }

    @Test
    void addItem() {

        assertTrue(inventory.getItems().contains(Items.ancientKey));
    }

    @Test
    void removeItem() {
        inventory.removeItem(Items.ancientKey);
        assertTrue(inventory.isEmpty());
    }
}