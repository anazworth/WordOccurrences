package wordoccurrences;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is responsible for launching the application, as well as creating the GUI.
 * It also contains the main method.
 *
 * @author Austin Nazworth
 * @version 1.0
 */
public class Main extends Application{

    /**
     * This method is responsible for launching the graphical user interface.
     * It is called by the main method.
     *
     * @param primaryStage The primary stage for this application, from which the application scene can be set.
     * @throws Exception If the application cannot be launched.
     * @see javafx.application.Application#start(Stage)
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label hint = new Label("Please choose a text file to analyze: ");
        Label label = new Label("File:");
        TextField tf= new TextField();
        Button fileBrowserButton = new Button("Browse");
        Button analyzeButton = new Button("Analyze");
        Button clearButton = new Button("Clear");
        Label resultsLabel = new Label("Results:");
        TextArea results = new TextArea();


        fileBrowserButton.setOnAction(Handlers.openFile(primaryStage, tf, results));

        analyzeButton.setOnAction(Handlers.analyzeButtonHandler(tf, results));

        clearButton.setOnAction(e -> results.clear());

        VBox root = new VBox();
        HBox hintBox = new HBox();
        HBox fileChooserBox = new HBox();
        HBox resultViewBox = new HBox();

        hintBox.setPadding(new javafx.geometry.Insets(20,20,20,80));
        hintBox.getChildren().add(hint);

        fileChooserBox.setSpacing(20);
        fileChooserBox.setPadding(new javafx.geometry.Insets(20,20,20,40));
        fileChooserBox.getChildren().addAll(label,tf,analyzeButton,fileBrowserButton);

        resultViewBox.setSpacing(20);
        resultViewBox.setPadding(new javafx.geometry.Insets(20,20,20,20));
        resultViewBox.autosize();
        resultViewBox.getChildren().addAll(resultsLabel,results, clearButton);

        root.autosize();
        root.getChildren().addAll(hintBox,fileChooserBox,resultViewBox);

        Scene scene = new Scene(root,700,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Word Occurrences");
        primaryStage.show();

    }

    /**
     * This method is responsible for launching the application.
     *
     * @param args The command line arguments. Should not be used in this context.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
