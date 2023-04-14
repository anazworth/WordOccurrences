package wordoccurrences;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordDAO {
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordoccurrences?", "wordoccurrences", "changeme");

    public WordDAO() throws SQLException {
    }

//    public void addWord(Word word) throws SQLException {
//        PreparedStatement statement = connection.prepareStatement("INSERT INTO word (word, count) VALUES (?, ?)");
//        statement.setString(1, word.word());
//        statement.setInt(2, word.count());
//        statement.executeUpdate();
//    }

    public Word get(String word) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM word WHERE word = ?");
        statement.setString(1, word);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Word(resultSet.getString("word"), resultSet.getInt("count"));
        } else {
            return null;
        }
    }

    public void save(String word) throws SQLException {
        Word wordCheck = get(word);
        PreparedStatement statement;
        if (wordCheck != null) {
            statement = connection.prepareStatement("UPDATE word SET count = count + 1 WHERE word = ?");
        } else {
            statement = connection.prepareStatement("INSERT INTO word (word, count) VALUES (?, 1)");
        }
        statement.setString(1, word);
        statement.executeUpdate();
    }

    public List<Word> getTopWords(int limit) throws SQLException {
        List<Word> results = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM word ORDER BY count DESC LIMIT ?");
        statement.setInt(1, limit);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            results.add(new Word(resultSet.getString("word"), resultSet.getInt("count")));
        }
        return results;
    }

    public void removeAllWords() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM word");
        statement.executeUpdate();
    }
}
