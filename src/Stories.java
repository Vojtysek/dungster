import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Stories {


    public static Story ChamberDialogue() {
        Story root = new Story("""
                Probudíš se v potemnělé komoře. Ticho je téměř hmatatelné.
                Na tvém zápěstí cítíš tíhu kovu. Před tebou stojí masivní dveře pokryté symboly.\s
                """);
        Story tryDoorBefore = new Story("Snažíš se otevřít dveře, ale jsou zapečetěné. Něco jim chybí.\n").setFollowUp(root);
        Story tryDoorAfter = new Story("Dveře se s tichým syknutím pootevřou. Cesta dál je volná.\n").unlockNewRoom(Rooms.Puppets);
        Story examineBracelet = new Story("Zvedáš ruku a zkoumáš náramek. Je studený na dotek, ale pulzuje slabým světlem. " + "Každý slot má nad sebou vyryté slovo: Čas. Paměť. Pravda. Volba.\n").thenAddItem(Items.braceletThreads);
        Story checkSelf = new Story("Prohledat se").setFollowUp(examineBracelet);
        Story examineDoor = new Story("Otevřít dveře").setFollowUp(tryDoorBefore);
        Story checkDoor = new Story("Otevřít dveře").setFollowUp(tryDoorAfter);

        root.setChoices(List.of(checkSelf, examineDoor));
        examineBracelet.setChoices(List.of(checkDoor));

        return root;
    }

    public static Story PuppetsDialogue() {
        Story root = new Story("Vstupuješ do místnosti plné loutek visících ze stropu. Všude je prach a ticho, narušované jen slabým skřípáním dřeva. " + "Uprostřed místnosti stojí vysoký muž v černém kabátu. Jeho tvář je skrytá pod porcelánovou maskou. " + "„Konečně,“ promluví loutkář hlubokým hlasem. „Chceš-li pokračovat, odpověz mi: “\n");
        Story riddle = new Story("„Mám klíč, ale nezamykám. Mám prostor, ale nezabírám místo. " + "Mohu být čten, ale nejsem kniha. Co jsem?“\n");
        Story wrongAnswer = new Story("Loutkář mlčky zavrtí hlavou. „To není správně.“ Loutky se zachvějí, jako by se vysmívaly tvé chybě.\n").setFollowUp(riddle);
        Story correctAnswer = new Story("„Správně,“ přikývne loutkář a z rukávu vytáhne drobný kámen zářící modrým světlem. " + "„Tento kámen představuje Paměť. Budeš ji potřebovat.“\n").thenAddItem(Items.threadMemory).unlockNewRoom(Rooms.Voices);
        Story answerKeyboard = new Story("Klávesnice").setFollowUp(correctAnswer);
        Story answerBook = new Story("Kniha").setFollowUp(wrongAnswer);
        Story answerMap = new Story("Mapa").setFollowUp(wrongAnswer);

        riddle.setChoices(List.of(answerKeyboard, answerBook, answerMap));
        root.setFollowUp(riddle);

        return root;
    }

    public static Story WhispersDialogue() {
        Story root = new Story("""
                Vstoupíš do potemnělé místnosti. Stěny jsou pokryté slovy — stovkami, možná tisíci slov, vyškrábaných do kamene.\s
                Místností se ozývá šepot, hlasitější, než bys čekal. Slova se ti míhají před očima – zmatená, chaotická, bolestivá.
                """);

        Story reading = new Story("Přistoupíš blíž ke zdi. " + "Slova se v tobě začínají usazovat. Některá dávají smysl, jiná jsou jako útržky snu.\n");

        Story search = new Story("""
                Začneš slova zkoumat. „volba... zrcadlo... paměť... konec... lež... pravda... smrt... pravda...“\s
                Jedno z nich se ti zdá důležité. Kterého se dotkneš?
                """);

        Story guessRight = new Story("""
                Z místa, kde jsi se dotkl stěny, se uvolní malý krystal – kámen zářící klidným, zlatavým světlem.\s
                „Toto je kámen Pravdy,“ zašeptá ti hlas do ucha, ale nikdo kolem není.
                """).thenAddItem(Items.threadTruth).unlockNewRoom(Rooms.Mirrors);

        Story guessWrong = new Story("Dotkneš se slova, ale nic se nestane. Šepot sílí, až tě nutí couvnout. Zdá se, že to nebylo to pravé.\n").setFollowUp(search);

        Story guessFatal = new Story("""
                Dotkneš se slova "smrt". Místnost se náhle ponoří do tmy. Šepot se změní v ohlušující křik.
                Cítíš, jak tě slova začínají fyzicky svírat, jako by se stala hmotou.
                Poslední, co slyšíš, je: "Některá slova by měla zůstat nevyslovena..."
                """).gameOver("Byl jsi pohlcen slovy, která neměla být vyslovena.");

        Story choiceTruth = new Story("Pravda").setFollowUp(guessRight);
        Story choiceLie = new Story("Lež").setFollowUp(guessWrong);
        Story choiceEnd = new Story("Konec").setFollowUp(guessWrong);
        Story choiceDeath = new Story("Smrt").setFollowUp(guessFatal);

        search.setChoices(List.of(choiceTruth, choiceLie, choiceEnd, choiceDeath));
        reading.setFollowUp(search);
        root.setFollowUp(reading);

        return root;
    }

    public static Story MirrorsDialogue() throws IOException {
        Story root = new Story("""
                Vstupuješ do Síně zrcadel. Na první pohled je prázdná, ale světlo z tvé přítomnosti se odráží nespočetněkrát.
                Zrcadla jsou všude. Některá tě ukazují staršího, jiná cizince. Některá se nehýbou, i když ty ano.
                """);

        Story mazeIntro = new Story("""
                Po několika krocích si uvědomíš, že nejsi v místnosti, ale v bludišti vytvořeném ze zrcadel.
                Na zemi si všimneš škrábanců, které možná někdo před tebou zanechal jako vodítko.
                """);

        Story mazeDisplay = new Story("Před tebou se rýsuje symbolická mapa bludiště. Musíš najít cestu ven.\n");

        Story playMaze = new Story("") {
            @Override
            public String getLine() {
                try {
                    Maze maze = new Maze();
                    maze.playMaze();
                } catch (IOException | InterruptedException e) {
                    System.out.println("Chyba při hraní bludiště: " + e.getMessage());
                }
                return "Dokončil jsi bludiště zrcadel. Cesta dál se otevírá...";
            }
        };

        Story crystalLady = new Story("""
                Z jedné z chodeb vystoupí postava. Žena, její tělo tvořené krystaly, oči svítící bílým světlem.
                „Zrcadla ti neukazují, kým jsi, ale kým bys mohl být. V každém z nich je odraz volby, kterou jsi nikdy neučinil."
                """);

        Story revelation = new Story("„Ne všechny odpovědi leží na konci cesty," + "Některé nosíš s sebou od samého počátku. Hledej své chyby ve vlastním odrazu.\n" + "Její obraz se pomalu rozplyne ve skle. Zůstanou jen tvé oči hledící zpět.\n");

        Story makeChoice = new Story("Před tebou se objeví dvě zrcadla. Jedno ukazuje cestu vpřed, druhé cestu zpět. Které si vybereš?\n");

        Story chooseForward = new Story("Vybral jsi cestu vpřed").setFollowUp(new Story("Zanechán s novým poznáním, otáčíš se zpět a pokračuješ dál.\n").unlockNewRoom(Rooms.Mecha));

        Story chooseBackward = new Story("Vybral jsi cestu zpět").setFollowUp(new Story("""
                Zrcadlo se roztříští a střepy tě obklopí. Cítíš, jak se ti do kůže zařezávají tisíce malých řezů.
                Tvůj odraz se násobí do nekonečna, každý střep ukazuje jinou verzi tebe.
                Poslední, co vidíš, je tvůj vlastní vyděšený výraz v tisíci odrazech.
                """).gameOver("Ztratil jsi se v nekonečných odrazech zrcadel."));

        makeChoice.setChoices(List.of(chooseForward, chooseBackward));

        root.setFollowUp(mazeIntro);
        mazeIntro.setFollowUp(mazeDisplay);
        mazeDisplay.setFollowUp(Main.dev ? crystalLady : playMaze);
        playMaze.setFollowUp(crystalLady);
        crystalLady.setFollowUp(revelation);
        revelation.setFollowUp(makeChoice);

        return root;
    }

    public static Story MechaDialogue() {
        Story root = new Story("Vcházíš do místnosti plné kovu a šepotu ozubených kol. Stroje tu žijí vlastním životem, i když tu nikdo není.\n");

        Story inspect = new Story("Na stole leží nefunkční zařízení s dutinou přesně ve tvaru kamene. Vedle něj je poznámka:\n*\"Čas je klíč, ale nelze ho otočit.\"*\n");

        Story repair = new Story("Vkládáš kámen do otvoru. Mechanismus se aktivuje a stroje se probudí. Čas se jakoby na chvíli otočí. Ve tvém náramku se objeví poslední kámen – Čas.\n").thenAddItem(Items.threadTime).unlockNewRoom(Rooms.Knots);

        Story useGem = new Story("Použít kámen Času").setFollowUp(repair);

        inspect.setChoices(List.of(useGem));
        root.setFollowUp(inspect);

        return root;
    }

    public static Story KnotsDialogue() {
        Story root = new Story("Stěny této komory jsou pokryty stovkami tenkých provazů. Vzduch je plný napětí – každý uzel vibruje vlastním rytmem.\n");

        Story observe = new Story("Na podstavci uprostřed je jednoduchý návod: *\"Rozvaž ten, který drží paměť pohromadě.\"*\n");

        Story wrong = new Story("Rozvážeš uzel. Místností projde vlna chaosu. Provazy se stáhnou a zablokují cestu zpět.\n").setFollowUp(observe);

        Story correct = new Story("Dotkneš se jediného uzlu s vláknem ze stříbra. Jakmile ho rozvážeš, provazy ztichnou. Dveře se otevřou dál do hlubin komplexu.\n").unlockNewRoom(Rooms.Lies);

        Story option1 = new Story("Rozvázat ten, co pulzuje červeně").setFollowUp(wrong);
        Story option2 = new Story("Rozvázat ten, co je z ledu").setFollowUp(wrong);
        Story option3 = new Story("Rozvázat stříbrný uzel").setFollowUp(correct);

        observe.setChoices(List.of(option1, option2, option3));
        root.setFollowUp(observe);

        return root;
    }

    public static Story LiesDialogue() {
        Story root = new Story("Vstoupíš do rozlehlé knihovny. Regály se tyčí až ke stropu, plné zaprášených knih. Pach starého papíru a inkoustu je téměř dusivý. Ticho je těžké, jako by každá kniha čekala, až ji otevřeš.\n");

        Story explore = new Story("Procházíš mezi regály, letmo čteš názvy: *\"Vzpomínky, které nebyly\"*, *\"Pravda, jakou si přeješ\"*, *\"Stíny toho, kým jsi byl\"*.\nNěco tě nutí vybrat jednu z knih.\n");

        Story pickTruth = new Story("Otevřeš knihu *\"Stíny toho, kým jsi byl\"*. Stránky šeptají tvým hlasem. Vzpomínky, které jsi potlačil, se ti vracejí. V každém řádku vidíš pravdu – neúplnou, ale skutečnou.\n");

        Story pickLie1 = new Story("Otevřeš knihu *\"Vzpomínky, které nebyly\"*. Příběhy v ní jsou lákavé, ale při čtení se rozplývají. Cítíš, že ztrácíš pevnou půdu pod nohama.\n").setFollowUp(explore);

        Story pickLie2 = new Story("Otevřeš *\"Pravdu, jakou si přeješ\"*. Je krásná. Příliš krásná. Ale něco uvnitř tě bodne – víš, že tohle není tvůj příběh.\n").setFollowUp(explore);

        Story readingPage = new Story("Jakmile stránku dočteš, ozve se z hlubin knihovny klapnutí. Dveře na konci sálu se pomalu otevírají.\n").unlockNewRoom(Rooms.Choices);

        Story reward = new Story("Z knihy vypadne stránka. Je to ztracená stránka, pokrytá podivnými symboly. Když ji vezmeš do ruky, cítíš, jak se ti v těle rozlévá teplo.\n").thenAddItem(Items.lostPage).setFollowUp(readingPage);

        Story book1 = new Story("Vzpomínky, které nebyly").setFollowUp(pickLie1);
        Story book2 = new Story("Stíny toho, kým jsi byl").setFollowUp(pickTruth.setFollowUp(reward));
        Story book3 = new Story("Pravda, jakou si přeješ").setFollowUp(pickLie2);

        explore.setChoices(List.of(book1, book2, book3));
        root.setFollowUp(explore);

        return root;
    }

    public static Story ChoicesDialogue() {
        Story root = new Story("Před tebou se rozprostírá kruhová místnost se čtyřmi dveřmi. Nad každými je napsáno jediné slovo: *Strach*, *Touha*, *Paměť*, *Volba*.\n");

        Story reflect = new Story("Ozve se hlas: \"Vyber jen jedny. Ale i nevolba je volbou.\"\n");

        Story end = new Story("Jakmile projdeš vybranými dveřmi, vše kolem potemní a ty se ocitáš před poslední branou.\n").unlockNewRoom(Rooms.Doomsday);

        Story choice1 = new Story("Strach").setFollowUp(end);
        Story choice2 = new Story("Touha").setFollowUp(end);
        Story choice3 = new Story("Paměť").setFollowUp(end);
        Story choice4 = new Story("Volba").setFollowUp(new Story("Zvolil jsi Volbu. V tvém náramku se zablýská nový kámen, který se sám zasune na své místo.").thenAddItem(Items.threadChoice).setFollowUp(end));


        reflect.setChoices(List.of(choice1, choice2, choice3, choice4));
        root.setFollowUp(reflect);

        return root;
    }

    public static Story DoomsdayDialogue() {
        Story root = new Story("Před tebou stojí masivní vrata, pokrytá rytinami všech cest, které jsi urazil. Zdá se, že reagují na přítomnost náramku i jeho kameny.\n");

        Story check = new Story("""
                Náramek ti svítí na zápěstí. Sloty s nápisy Čas, Paměť, Pravda a Volba pulzují různě silně.
                Pokud nejsou všechny naplněny, poslední dveře ti pravdu neukážou.
                """).unlockNewRoom(Rooms.Fate);

        root.setFollowUp(check);
        return root;
    }

    public static Story FateDialogue() {
        return new Story("") {
            @Override
            public String getLine() {
                boolean hasAll = Main.player.getInventory().hasItem(Items.threadMemory) && Main.player.getInventory().hasItem(Items.threadTruth) && Main.player.getInventory().hasItem(Items.threadChoice) && Main.player.getInventory().hasItem(Items.threadTime);

                if (hasAll) {
                    endGame();
                    return """
                            V kruhové síni stojí obrovské zrcadlo. Neodráží okolí – jen tebe. A ne tebe, jaký jsi… ale jaký bys mohl být.
                            Jakmile poslední kámen zapadne na místo, zrcadlo se rozzáří a ukáže ti tvou pravou podobu. Poznal jsi všechny nitě osudu.
                            """;
                } else {
                    return "Zrcadlo zůstává temné. Něco ti chybí – poslední nit osudu.\n Musíš se vrátit a dokončit svou cestu.\n";
                }
            }
        };
    }

}
