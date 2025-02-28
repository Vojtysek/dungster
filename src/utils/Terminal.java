package utils;

import dungster.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal {

    public static void typeWriter(String text, int sleepTime) throws InterruptedException {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(Main.dev ? 0 : 5);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        Thread.sleep(sleepTime);
        clearScreen();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void InvalidChoice() throws InterruptedException {
        clearScreen();
        System.out.println("Invalid choice. Please select a valid option.");
        Thread.sleep(1250);
    }

    public static void waitForInteraction() throws IOException {
        new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    public static void printEmpty() {
        System.out.println();
    }
}
