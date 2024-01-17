package com.example.sosknop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Wachtwoord {

    @FXML
    private TextField wachtwoord;

    @FXML
    private TextField nieuwe;

    @FXML
    private TextField herhaal;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToProfiel(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("welkom-screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private boolean areFieldsFilled() {
        return  !nieuwe.getText().isEmpty() &&
                !wachtwoord.getText().isEmpty() &&
                !herhaal.getText().isEmpty();
    }

    private boolean doPasswordsMatch() {
        return nieuwe.getText().equals(herhaal.getText());
    }

    public void updateWachtwoord(ActionEvent event) throws IOException {
        if (areFieldsFilled()) {
            if (doPasswordsMatch()) {
                if (Database.updatePassword(this.wachtwoord.getText(), this.nieuwe.getText())) {
                    root = FXMLLoader.load(getClass().getResource("welkom-screen.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else showAlert("Error, neem contact op met klantenservice.");
            } else  showAlert("Wachtwoord niet goed herhaal. Probeer opnieuw.");
        } else showAlert("Vul alstublieft alle velden in.");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

