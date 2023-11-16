module com.javagui.sosknop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.javagui.sosknop to javafx.fxml;
    exports com.javagui.sosknop;
}