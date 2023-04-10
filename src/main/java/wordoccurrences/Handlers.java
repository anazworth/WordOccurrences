package wordoccurrences;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**
 * This class is responsible for handling events.
 * It contains methods that are called when a button is pressed.
 *
 * @author Austin Nazworth
 */
public class Handlers {
    /**
     * This method is responsible for handling the event when the analyze button is pressed.
     * It calls the analyzeFile method, which analyzes the file and displays the top 20 words in the results text area.
     *
     * @param tf The text field that contains the path to the file to be analyzed.
     * @param results The text area that displays the results of the analysis.
     * @return The event handler for the analyze button.
     */
    static EventHandler<ActionEvent> analyzeButtonHandler(TextField tf, TextArea results) {
        return e -> {
            if (tf.getText().equals("")) {
                results.setText("Please choose a file first!");
                return;
            }
            analyzeFile(tf, results);
        };
    }

    /**
     * This method is responsible for analyzing the file and displaying the top 20 words in the results text area.
     *
     * @param tf The text field that contains the path to the file to be analyzed.
     * @param results The text area that displays the results of the analysis.
     */
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

    /**
     * This method is responsible for handling the event when the browse button is pressed.
     * It opens a file chooser and allows the user to select a file.
     * When a file is chosen, it then calls the analyzeFile method, which analyzes the file and displays the top 20 words in the results text area.
     *
     * @param primaryStage The primary stage for this application, from which the application scene can be set.
     * @param tf The text field that contains the path to the file to be analyzed.
     * @param results The text area that displays the results of the analysis.
     * @return The event handler for the browse button.
     */
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
