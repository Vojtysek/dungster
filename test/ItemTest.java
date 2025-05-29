import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    void getItemName() {
        Assertions.assertEquals("Lost Page", Items.lostPage.getItemName());
    }

    @Test
    void getItemDescription() {
        Assertions.assertEquals("A page from a book that seems to be lost.", Items.lostPage.getItemDescription());
    }

    @Test
    void getItemType() {
        Assertions.assertEquals("Page", Items.lostPage.getItemType());
    }
}