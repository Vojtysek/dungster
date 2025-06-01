import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Story {
    private final String line;
    private transient List<Consumer<Void>> pendingActions = new ArrayList<>();
    private Story followUp = null;
    private List<Story> choices = new ArrayList<>();
    private boolean wasDisplayed = false;

    public Story(String line) {
        this.line = line;
    }

    public String getLine() {
        if (!wasDisplayed) {
            wasDisplayed = true;
            executePendingActions();
        }
        return line;
    }

    private void executePendingActions() {
        for (Consumer<Void> action : pendingActions) {
            action.accept(null);
        }
        pendingActions.clear();
    }

    public Story getFollowUp() {
        return followUp;
    }

    public Story setFollowUp(Story followUp) {
        this.followUp = followUp;
        return this;
    }

    public Story thenAddItem(Item item) {
        pendingActions.add((v) -> Main.player.getInventory().addItem(item));
        return this;
    }

    public Story endGame() {
        pendingActions.add((v) -> {
            try {
                System.out.println("Konec hry. Děkujeme za hraní!");
                Thread.sleep(3000); // Give player time to read the message
                System.exit(0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        return this;
    }

    public Story unlockNewRoom(Room room) {
        pendingActions.add((v) -> {
            Main.player.getCurrentRoom().setDone(true);
            room.setVisibility();
        });
        return this;
    }

    public List<Story> getChoices() {
        return choices;
    }

    public void setChoices(List<Story> choices) {
        this.choices = choices;
    }

    public Story gameOver(String reason) {
        pendingActions.add((v) -> {
            TerminalUtils.clearScreen();
            System.out.println("GAME OVER: " + reason);
            System.out.println("Hodně štěstí příště!");
            System.exit(0);
        });
        return this;
    }
}
