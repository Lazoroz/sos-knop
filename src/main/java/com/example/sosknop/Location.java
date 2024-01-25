package com.example.sosknop;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Location {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLocation() {
        MicroBitSerialReader.main();
        showAlert("Locatie opgeslagen");

    }

    public void switchToOverzicht(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("contact-overzicht-screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToWelkom(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("welkom-screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

        private void showAlert(String message) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
}



