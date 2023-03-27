package wordoccurrences;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class Handlers {
    static EventHandler<ActionEvent> analyzeButtonHandler(TextField tf, TextArea results) {
        return e -> {
            if (tf.getText().equals("")) {
                results.setText("Please choose a file first!");
                return;
            }
            analyzeFile(tf, results);
        };
    }

    private static void analyzeFile(TextField tf, TextArea results) {
        try {
            List<String> resultsList = TextAnalyzer.analyzeText(tf.getText());
            results.clear();
            for (String result : resultsList) {
                results.appendText(result + "\n");
            }
        } catch (Exception ex) {
            results.setText("Please choose a valid file!");
        }
    }

    static EventHandler<ActionEvent> openFile(Stage primaryStage, TextField tf, TextArea results) {
        return e ->
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null) {
                results.clear();
                tf.setText(file.getAbsolutePath());
                analyzeFile(tf, results);
            }else {
                results.setText("Please choose a valid file!");
            }
        };
    }
}
