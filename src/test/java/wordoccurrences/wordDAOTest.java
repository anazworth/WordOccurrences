package wordoccurrences;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordDAOTest {
    static WordDAO repo;

    @BeforeEach
    void setup() throws SQLException {
        repo = new WordDAO();
        repo.removeAllWords();
        repo.save("test");
        repo.save("test");
        repo.save("secondWord");
        repo.save("thirdWord");
    }

    @Test
    void getWord() throws SQLException {
        String expectedWord = "test";

        Word result = repo.get(expectedWord);

        assertEquals(expectedWord, result.word());
        assertEquals(2, result.count());
    }

    @Test
    void insertWord() throws SQLException {
        String word = "inserted";

        try {
            repo.save(word);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Word result = repo.get(word);

        assertEquals(word, result.word());
        assertEquals(1, result.count());
    }

    @Test
    void getTopWords() throws SQLException {
        int limit = 2;
        String expectedWord = "test";

        List<Word> results = repo.getTopWords(limit);
        System.out.println(results);

        assertEquals(expectedWord, results.get(0).word());
        assertEquals(2, results.get(0).count());
        assertEquals("secondWord", results.get(1).word());
        assertEquals(1, results.get(1).count());
        // Check that the list is the correct size
        assertEquals(2, results.size());
    }

    @Test
    void removeAllWords() throws SQLException {
        repo.removeAllWords();
        List<Word> results = repo.getTopWords(10);
        assertEquals(0, results.size());
    }
}