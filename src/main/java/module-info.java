module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens wordoccurrences to javafx.fxml;
    exports wordoccurrences;
}