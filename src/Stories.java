import java.util.List;

public class Stories {


    public static Story ChamberDialogue() {
        Story root = new Story("Probudíš se v potemnělé komoře. Ticho je téměř hmatatelné. " + "Na tvém zápěstí cítíš tíhu kovu. Před tebou stojí masivní dveře pokryté symboly.");

        Story tryDoorBefore = new Story("Snažíš se otevřít dveře, ale jsou zapečetěné. Něco jim chybí.").setFollowUp(root);

        Story firstWalkThrough = new Story("Procházíš dveřmi a ocitáš se v chladné chodbě. " + "Na stěnách visí obrazy, které se zdají sledovat každý tvůj pohyb. Po 50m se dostáváš do další místnosti.");
        Story tryDoorAfter = new Story("Dveře se s tichým syknutím pootevřou. Cesta dál je volná.").setFollowUp(firstWalkThrough);
        Story examineBracelet = new Story("Zvedáš ruku a zkoumáš náramek. Je studený na dotek, ale pulzuje slabým světlem. " + "Každý slot má nad sebou vyryté slovo: Čas. Paměť. Pravda. Volba.").thenAddItem(Items.braceletThreads04);

        Story checkSelf = new Story("Prohledat se").setFollowUp(examineBracelet);
        Story examineDoor = new Story("Otevřít dveře").setFollowUp(tryDoorBefore);
        Story checkDoor = new Story("Otevřít dveře").setFollowUp(tryDoorAfter);

        root.setChoices(List.of(checkSelf, examineDoor));
        examineBracelet.setChoices(List.of(checkDoor));

        return root;
    }
}
