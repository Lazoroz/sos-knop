module com.example.sosknop {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.web;
    requires java.sql;
    requires com.google.gson;
    requires com.fazecast.jSerialComm;

    opens com.example.sosknop to javafx.fxml, com.google.gson;

    exports com.example.sosknop;

}
