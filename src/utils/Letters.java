package utils;

import java.util.HashMap;
import java.util.Map;

public class Letters {

//    Maybe later, this is to complex

//    private static final int HEIGHT = 8;
//    private static final Map<Character, String[]> FONT = new HashMap<>();
//
//    static {
//        FONT.put('a', new String[]{
//                "        ",
//                "        ",
//                "   __ _ ",
//                "  / _` |",
//                " | (_| |",
//                "  \\__,_|",
//                "        ",
//                "        "
//        });
//        FONT.put('b', new String[]{
//                "  _     ",
//                " | |    ",
//                " | |__  ",
//                " | '_ \\ ",
//                " | |_) |",
//                " |_.__/ ",
//                "        ",
//                "        "
//        });
//        FONT.put('c', new String[]{
//                "       ",
//                "       ",
//                "   ___ ",
//                "  / __|",
//                " | (__ ",
//                "  \\___|",
//                "       ",
//                "       "
//        });
//        FONT.put('d', new String[]{
//                "      _ ",
//                "     | |",
//                "   __| |",
//                "  / _` |",
//                " | (_| |",
//                "  \\__,_|",
//                "        ",
//                "        "
//        });
//        FONT.put('e', new String[]{
//                "       ",
//                "       ",
//                "  ___  ",
//                " / _ \\ ",
//                "|  __/ ",
//                " \\___| ",
//                "       ",
//                "       "
//        });
//        FONT.put('f', new String[]{
//                "  __ ",
//                " / _|",
//                "| |_ ",
//                "|  _|",
//                "| |  ",
//                "|_|  ",
//                "     ",
//                "     "
//        });
//        FONT.put('g', new String[]{
//                "        ",
//                "        ",
//                "   __ _ ",
//                "  / _` |",
//                " | (_| |",
//                "  \\__, |",
//                "   __/ |",
//                "  |___/ "
//        });
//        FONT.put('h', new String[]{
//                "  _     ",
//                " | |    ",
//                " | |__  ",
//                " | '_ \\ ",
//                " | | | |",
//                " |_| |_|",
//                "        ",
//                "        "
//        });
//        FONT.put('i', new String[]{
//                "  _ ",
//                " (_)",
//                "  _ ",
//                " | |",
//                " | |",
//                " |_|",
//                "    ",
//                "    "
//        });
//        FONT.put('j', new String[]{
//                "     _ ",
//                "    (_)",
//                "     _ ",
//                "    | |",
//                "    | |",
//                "   _/ |",
//                "  |__/ ",
//                "       "
//        });
//        FONT.put('k', new String[]{
//                "  _    ",
//                " | |   ",
//                " | | __",
//                " | |/ /",
//                " |   < ",
//                " |_|\\_\\",
//                "       ",
//                "       "
//        });
//        FONT.put('l', new String[]{
//                "  _ ",
//                " | |",
//                " | |",
//                " | |",
//                " | |",
//                " |_|",
//                "    ",
//                "    "
//        });
//        FONT.put('m', new String[]{
//                "           ",
//                "           ",
//                " _ __ ___  ",
//                "| '_ ` _ \\ ",
//                "| | | | | |",
//                "|_| |_| |_|",
//                "           ",
//                "           "
//        });
//        FONT.put('n', new String[]{
//                "       ",
//                "       ",
//                " _ __  ",
//                "| '_ \\ ",
//                "| | | |",
//                "|_| |_|",
//                "       ",
//                "       "
//        });
//        FONT.put('o', new String[]{
//                "        ",
//                "        ",
//                "  ___   ",
//                " / _ \\  ",
//                "| (_) | ",
//                " \\___/  ",
//                "        ",
//                "        "
//        });
//        FONT.put('p', new String[]{
//                "       ",
//                "       ",
//                " _ __  ",
//                "| '_ \\ ",
//                "| |_) |",
//                "| .__/ ",
//                "| |    ",
//                "|_|    "
//        });
//        FONT.put('q', new String[]{
//                "        ",
//                "        ",
//                "  __ _  ",
//                " / _` | ",
//                "| (_| | ",
//                " \\__, | ",
//                "    | | ",
//                "    |_| "
//        });
//        FONT.put('r', new String[]{
//                "       ",
//                "       ",
//                " _ __  ",
//                "| '_ \\ ",
//                "| |_) |",
//                "| .__/ ",
//                "| |    ",
//                "|_|    "
//        });
//        FONT.put('s', new String[]{
//                "      ",
//                "      ",
//                "  ___ ",
//                " / __|",
//                " \\__ \\",
//                " |___/",
//                "      ",
//                "      "
//        });
//        FONT.put('t', new String[]{
//                " _   ",
//                "| |  ",
//                "| |_ ",
//                "| __|",
//                "| |_ ",
//                " \\__|",
//                "     ",
//                "     "
//        });
//        FONT.put('u', new String[]{
//                "        ",
//                "        ",
//                " _   _  ",
//                "| | | | ",
//                "| |_| | ",
//                " \\__,_| ",
//                "        ",
//                "        "
//        });
//        FONT.put('v', new String[]{
//                "        ",
//                "        ",
//                "__   __ ",
//                "\\ \\ / / ",
//                " \\ V /  ",
//                "  \\_/   ",
//                "        ",
//                "        "
//        });
//        FONT.put('w', new String[]{
//                "          ",
//                "          ",
//                "__      __",
//                "\\ \\ /\\ / /",
//                " \\ V  V / ",
//                "  \\_/\\_/  ",
//                "          ",
//                "          "
//        });
//        FONT.put('x', new String[]{
//                "       ",
//                "       ",
//                "__  __ ",
//                "\\ \\/ / ",
//                " >  <  ",
//                "/_/\\_\\ ",
//                "       ",
//                "       "
//        });
//        FONT.put('y', new String[]{
//                "       ",
//                "       ",
//                "__   __",
//                "\\ \\ / /",
//                " \\ V / ",
//                "  | |  ",
//                "  |_|  ",
//                "       "
//        });
//        FONT.put('z', new String[]{
//                "      ",
//                "      ",
//                " ____ ",
//                "|_  / ",
//                " / /  ",
//                "/___| ",
//                "      ",
//                "      "
//        });
//        FONT.put(' ', new String[]{
//                "   ",
//                "   ",
//                "   ",
//                "   ",
//                "   ",
//                "   ",
//                "   ",
//                "   "
//        });
//    }
//
//    public static void printBigAsciiHorizontal(String data) {
//        data = data.toLowerCase();
//        StringBuilder[] output = new StringBuilder[HEIGHT];
//        for (int i = 0; i < HEIGHT; i++) {
//            output[i] = new StringBuilder();
//        }
//
//        for (char ch : data.toCharArray()) {
//            String[] block = FONT.getOrDefault(ch, FONT.get(' '));
//            for (int i = 0; i < HEIGHT; i++) {
//                output[i].append(block[i]).append("  "); // Mezery mezi písmeny
//            }
//        }
//
//        for (StringBuilder line : output) {
//            System.out.println(line);
//        }
//    }
//
//    public static void typeWriterAscii(String text, int sleepTime) throws InterruptedException {
//        text = text.toLowerCase();
//        StringBuilder[] output = new StringBuilder[HEIGHT];
//        for (int i = 0; i < HEIGHT; i++) {
//            output[i] = new StringBuilder();
//        }
//
//        for (char ch : text.toCharArray()) {
//            String[] block = FONT.getOrDefault(ch, FONT.get(' '));
//            for (int i = 0; i < HEIGHT; i++) {
//                output[i].append(block[i]).append("  "); // Mezery mezi písmeny
//            }
//        }
//
//        for (StringBuilder line : output) {
//            for (char c : line.toString().toCharArray()) {
//                System.out.print(c);
//                Thread.sleep(sleepTime);
//            }
//            System.out.println();
//        }
//    }
}