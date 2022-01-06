module com.example.hausaufgabe6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.management;

    opens com.example.hausaufgabe6 to javafx.fxml;
    exports com.example.hausaufgabe6;
}