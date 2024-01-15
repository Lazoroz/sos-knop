package com.example.sosknop;
import  javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class Login {


    @FXML
    private TextField email;

    @FXML
    private PasswordField wachtwoord;


    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToHome(ActionEvent event) throws IOException {
        // Checks that all fields are filled
        if (areFieldsFilled()) {

            if (Database.Login(this.email.getText(), this.wachtwoord.getText())) {
                    // Switch to the home screen
                    root = FXMLLoader.load(getClass().getResource("location-screen.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
            } else {
                 //Display an error message for incorrect login info
                 showAlert("Ongeldige inloggegevens. Probeer opnieuw.");
                }

        } else {
            // Display an error message or handle the validation failure
            showAlert("Vul alstublieft alle velden in.");
        }
    }

    public void switchToAanmeld(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("aanmeld-screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private boolean areFieldsFilled() {
        return  !email.getText().isEmpty() &&
                !wachtwoord.getText().isEmpty();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}