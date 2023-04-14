module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens wordoccurrences to javafx.fxml;
    exports wordoccurrences;
}