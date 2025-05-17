import java.util.ArrayList;
import java.util.List;

public class Story {
    private final String line;
    private Story followUp = null;
    private List<Story> choices = new ArrayList<>();

    public Story(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public Story getFollowUp() {
        return followUp;
    }

    public Story setFollowUp(Story followUp) {
        this.followUp = followUp;
        return this;
    }

    public Story thenAddItem(Item item) {
            Main.player.getInventory().addItem(item);
            return this;
    }

    public Story thenMovePlayer(Room room) {
        Main.player.setCurrentRoom(room);
        return this;
    }

    public List<Story> getChoices() {
        return choices;
    }

    public void setChoices(List<Story> choices) {
        this.choices = choices;
    }
}