import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Story implements Serializable {
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

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.pendingActions = new ArrayList<>();
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

    public void endGame() {
        pendingActions.add((v) -> {
            try {
                System.out.println("Konec hry. Děkujeme za hraní!");
                Thread.sleep(3000); // Give player time to read the message
                System.exit(0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public Story thenRemoveItem(Item item) {
        pendingActions.add((v) -> Main.player.getInventory().removeItem(item));
        return this;
    }

    public Story unlockNewRoom(Room room) {
        room.setVisibility();
        room.setDone(true);
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
            try {
                TerminalUtils.clearScreen();
                System.out.println("GAME OVER: " + reason);
                System.out.println("\nChceš hrát znovu? (a/n)");

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String input = reader.readLine().trim().toLowerCase();

                if (input.equals("a")) {
                    Main.player = new Player();
                    Rooms.initializeDialogues();
                    Main.player.setCurrentRoom(Rooms.Chamber);
                } else {
                    System.out.println("Děkujeme za hraní!");
                    Thread.sleep(2000);
                    System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Chyba: " + e.getMessage());
                System.exit(1);
            }
        });
        return this;
    }
}
