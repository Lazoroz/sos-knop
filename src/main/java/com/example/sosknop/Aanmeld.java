package com.example.sosknop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Aanmeld {

    @FXML
    private TextField voornaam;

    @FXML
    private  TextField tussenvoegsel;

    @FXML
    private TextField achternaam;


    @FXML
    private TextField telefoon;

    @FXML
    private TextField email;

    @FXML
    private PasswordField wachtwoord;

    @FXML
    private PasswordField herhaalwachtwoord;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToHome(ActionEvent event) throws IOException {
        // Checks that all fields are filled
        if (areFieldsFilled()) {
            // Checks that passwords match
            if (doPasswordsMatch()) {
                // Save information to the database
                if (emailIsValid()) {
                    if (Database.newUser(this.voornaam.getText(),this.tussenvoegsel.getText(), this.achternaam.getText(), this.telefoon.getText(), this.email.getText(), this.wachtwoord.getText() )) {
                        // Switch to the home screen
                        root = FXMLLoader.load(getClass().getResource("location-screen.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                } else {
                    // Display an error message for invalid email
                    showAlert("Ongeldig e-mailadres. Probeer opnieuw.");
                }
            } else {
                // Display an error message or handle the password mismatch
                showAlert("Wachtwoorden komen niet overeen. Probeer opnieuw.");
            }
        } else {
            // Display an error message or handle the validation failure
            showAlert("Vul alstublieft alle velden in.");
        }
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login-screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private boolean areFieldsFilled() {
        return !voornaam.getText().isEmpty() &&
                !achternaam.getText().isEmpty() &&
                !telefoon.getText().isEmpty()&&
                !email.getText().isEmpty() &&
                !wachtwoord.getText().isEmpty() &&
                !herhaalwachtwoord.getText().isEmpty();
    }

    private boolean emailIsValid() {
        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher m = p.matcher(email.getText());

        // Check if the email matches the pattern
        return m.matches();
    }


    private boolean doPasswordsMatch() {
        return wachtwoord.getText().equals(herhaalwachtwoord.getText());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}