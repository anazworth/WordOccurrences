import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TextAnalyzer {
    private String loadFile(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path))); // Read the file into a string in a way that is filetype agnostic
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> formatText(String text) {
        String trimmedText = text.trim();
        return Arrays.stream(trimmedText.split(" "))
                .map(word -> word.replaceAll("[^a-zA-Z']", "")) // Replace everything that is not a letter or an apostrophe
                .map(String::toLowerCase)
                .toList(); // Convert to a list to make it easier to Map and Reduce
    }

    private Map<String, Integer> countWords(List<String> words) {
        return words.stream()
                .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));
    }

    private void printTopWords(Map<String, Integer> wordCounts) {
        List<Map.Entry<String, Integer>> sortedWordCounts = wordCounts.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .toList();
        for (int i = 0; i < 20; i++) {
            System.out.println(i + 1 + ". " + sortedWordCounts.get(i));
        }
    }

    public static void analyzeText(String path) {
        // load the html file from the first argument
        TextAnalyzer analyzer = new TextAnalyzer();
        String text = analyzer.loadFile(path);
        List<String> words = analyzer.formatText(text);
        Map<String, Integer> wordCount = analyzer.countWords(words);
        analyzer.printTopWords(wordCount);
    }
}
