package wordoccurrences;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is responsible for analyzing text files.
 * <p>
 * This class is intended to be used through the analyzeText method.
 * <p>
 *
 * @author Austin Nazworth
 */
public class TextAnalyzer {
    /**
     * This method loads a file from the given path.
     *
     * @param path The path to the file to load.
     * @return The contents of the file as a string.
     * @throws Exception If the file is not found.
     */
    public String loadFile(String path) throws Exception {
        try {
            return new String(Files.readAllBytes(Paths.get(path))); // Read the file into a string in a way that is filetype agnostic
        } catch (Exception e) {
            throw new Exception("File not found.");
        }
    }

    /**
     * This method analyzes the given text and returns a list of the words.
     *
     * @param text The text to analyze.
     * @return A list of all the words.
     */
    public List<String> formatText(String text) {
        String trimmedText = text.trim();
        return Arrays.stream(trimmedText.split(" "))
                .map(word -> word.replaceAll("[^a-zA-Z']", "")) // Replace everything that is not a letter or an apostrophe
                .map(String::toLowerCase)
                .filter(word -> !word.equals("")) // Remove empty strings
                .toList(); // Convert to a list to make it easier to Map and Reduce
    }

    /**
     * This method counts the number of occurrences of each word in the given list.
     *
     * @param words The list of words to count.
     * @return A map of each word to the number of occurrences.
     */
    public Map<String, Integer> countWords(List<String> words) {
        return words.stream()
                .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));
    }

    /**
     * This method returns a list of the top 20 words in the given map.
     *
     * @param wordCounts A map of words to the number of occurrences.
     * @return A list of the top 20 words.
     */
    public List<String> topWords(Map<String, Integer> wordCounts) {
        List<String> results = new ArrayList<>();
        List<Map.Entry<String, Integer>> sortedWordCounts = wordCounts.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .toList();
        for (int i = 0; i < 20; i++) {
            if (i >= sortedWordCounts.size()) {
                break;
            }
            results.add(i + 1 + ". " + sortedWordCounts.get(i));
        }
        return results;
    }
    /** This method is used to run the program from the command line.
     *
     * @param path The command line arguments.
     * @return A list of strings containing the top 20 words and their number of occurrences.
     * @throws Exception If the file is not found.
     */
    public static List<String> analyzeText(String path) throws Exception {
        // load the html file from the first argument
        TextAnalyzer analyzer = new TextAnalyzer();
        String text = analyzer.loadFile(path);
        assert text != null;
        List<String> words = analyzer.formatText(text);
        Map<String, Integer> wordCount = analyzer.countWords(words);
        return analyzer.topWords(wordCount);
    }
}
