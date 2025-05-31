import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TerminalUtilsTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testTypeWriter() {
        TerminalUtils.typeWriter("Hello World");
        assertTrue(outputStream.toString().contains("Hello World"));
    }

    @Test
    void testInvalidChoiceOutput() throws IOException, InterruptedException {
        String simulatedInput = "\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        TerminalUtils.invalidChoice();
        assertTrue(outputStream.toString().contains("Invalid choice"));
    }

    @Test
    void testGetInput() {
        String input = "testInput\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        String result = TerminalUtils.getInput();
        assertEquals("testInput", result);
    }

    @Test
    void testPrintEmpty() {
        TerminalUtils.printEmpty();
        assertTrue(outputStream.toString().contains(System.lineSeparator()));
    }

    @Test
    void testClearScreenPrintsANSI() {
        TerminalUtils.clearScreen();
        assertTrue(outputStream.toString().contains("\033[H\033[2J"));
    }
}
