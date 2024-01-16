package com.example.sosknop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Toevoeg {
    @FXML
    private TextField voornaam;

    @FXML
    private TextField achternaam;

    @FXML
    private TextField telefoonnummer;

    @FXML
    private TextField tussenvoegsels;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToOverzicht(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("contact-overzicht-screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Toevoegen(ActionEvent event) throws IOException {
        // Checks that all fields are filled
        if (areFieldsFilled()) {
                    if (Database.newContact(this.voornaam.getText(), this.tussenvoegsels.getText(), this.achternaam.getText(), this.telefoonnummer.getText() )) {
                        // Switch to the home screen
                        root = FXMLLoader.load(getClass().getResource("contact-overzicht-screen.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
        } else {
            // Display an error message or handle the validation failure
            showAlert("Vul alstublieft alle velden in.");
        }
    }

    private boolean areFieldsFilled() {
        return !voornaam.getText().isEmpty() &&
                !achternaam.getText().isEmpty()&&
                !telefoonnummer.getText().isEmpty();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
