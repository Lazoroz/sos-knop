module com.example.sosknop {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.web;

    opens com.example.sosknop to javafx.fxml;

    exports com.example.sosknop;

}
