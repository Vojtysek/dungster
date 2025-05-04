import java.util.ArrayList;
import java.util.List;

public class Story {
    private String line;
    private List<Story> nextChoices = new ArrayList<>();

    public Story(String line) {
        this.line = line;
    }

    public void addNextChoice(Story choice) {
        nextChoices.add(choice);
    }

    public String getLine() {
        return line;
    }

    public List<Story> getNextChoices() {
        return nextChoices;
    }

    public boolean isTerminal() {
        return nextChoices.isEmpty();
    }

    public static class ChamberDialogue {
        public static Story create() {
            Story root = new Story("Probouzíš se na studené podlaze. Na stěnách slabě září symboly.");

            Story examineBracelet = new Story("Prohlížíš si náramek. Má čtyři prázdné sloty.");
            examineBracelet.addNextChoice(new Story("Drobné rytiny okolo slotů zobrazují čas, paměť, pravdu a volbu."));
            examineBracelet.addNextChoice(new Story("Náramek na tvém zápěstí na okamžik teple zavibruje."));

            Story readInscription = new Story("Čteš nápis: ‚Pouze ten, kdo spojí všechny Nitě osudu, může odejít.‘");
            readInscription.addNextChoice(new Story("Slova se na chvíli zalesknou a přeskupí."));

            Story approachDoor = new Story("Přistupuješ k zapečetěným dveřím. Jsou pokryté symboly.");
            approachDoor.addNextChoice(new Story("Dotýkáš se sigilu. Jemně se rozzáří."));
            approachDoor.addNextChoice(new Story("Nic se nestane."));

            root.addNextChoice(examineBracelet);
            root.addNextChoice(readInscription);
            root.addNextChoice(approachDoor);

            return root;
        }
    }
}