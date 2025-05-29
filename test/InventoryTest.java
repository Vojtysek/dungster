import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class InventoryTest {

    Inventory inventory = new Inventory();

    @Test
    void addItem() {
        inventory.addItem(Items.ancientKey);
        assertTrue(inventory.getItems().contains(Items.ancientKey));
    }

    @Test
    void removeItem() {
        inventory.addItem(Items.ancientKey);
        inventory.removeItem(Items.ancientKey);
        assertTrue(inventory.isEmpty());
    }
}