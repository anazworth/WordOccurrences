package wordoccurrences;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



/**
 * This class is responsible for testing the TextAnalyzer class.
 *
 * @author Austin Nazworth
 */
public class TextAnalyzerTest {

    private static TextAnalyzer analyzer;

    @BeforeAll
    public static void setup() {
        System.out.println("Starting tests...");
        analyzer = new TextAnalyzer();
    }
    @Test
    public void testLoadFile() throws Exception {
        // Setup
        String path = "src/test/resources/test.txt";

        // Test
        String result = analyzer.loadFile(path);

        // Assert
        assertEquals("Test file.", result);
    }

    @Test
    public void testLoadFileNull() {
        // Setup
        String path = "doesntexist.txt";

        // Test
        Exception exception = assertThrows(Exception.class, () -> analyzer.loadFile(path));

        // Assert
        assertEquals("File not found.", exception.getMessage());
    }

    @Test
    public void testFormatText() {
        // Setup
        String text = "This*  *** !is a test. This is !@##$% only a test.";
        List<String> expected = List.of("this", "is", "a", "test", "this", "is", "only", "a", "test");

        // Test
        List<String> result = analyzer.formatText(text);

        // Assert
        assertEquals(9, result.size());
        assertEquals(expected, result);
    }

    @Test
    public void testCountWords() {
        // Setup
        List<String> words = List.of("this", "is", "a", "test", "this", "is", "only", "a", "test");

        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("this", 2);
        expected.put("is", 2);
        expected.put("a", 2);
        expected.put("test", 2);
        expected.put("only", 1);

        // Test
        Map<String, Integer> result = analyzer.countWords(words);

        // Assert
        assertEquals(5, result.size());
        assertEquals(expected, result);
    }

    @Test
    public void testTopWords() {
        // Setup
        Map<String, Integer> setup = new HashMap<>();
        setup.put("this", 2);
        setup.put("is", 2);
        setup.put("a", 2);
        setup.put("test", 2);
        setup.put("only", 1);
        setup.put("biggest", 3);

        // Test
        List<String> result = analyzer.topWords(setup);

        // Assert
        assertEquals(6, result.size());
        assertEquals("1. biggest=3", result.get(0));
        assertEquals("6. only=1", result.get(5));
    }

    @Test
    public void testAnalyzeText() throws Exception {
        // Setup
        String path = "src/test/resources/TheRaven.txt";

        // Test
        List<String> result = TextAnalyzer.analyzeText(path);

        // Assert
        assertEquals(20, result.size());
        assertEquals("1. the=57", result.get(0));
        assertEquals("20. above=7", result.get(19));
    }

    @Test
    public void testAnalyzeTextNull() {
        // Setup
        String path = "doesntexist.txt";

        // Test
        Exception exception = assertThrows(Exception.class, () -> TextAnalyzer.analyzeText(path));

        // Assert
        assertEquals("File not found.", exception.getMessage());
    }
}
