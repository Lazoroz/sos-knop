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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profiel {

    @FXML
    private TextField voornaam;

    @FXML
    private TextField tussenvoegsel;

    @FXML
    private TextField achternaam;

    @FXML
    private TextField telefoon;

    @FXML
    private TextField email;

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
    public void opslaan(ActionEvent event) throws IOException {
        int loggedInUserId = sessionManager.getInstance().getLoggedInUserId();
        if (loggedInUserId != 0) {
            if (emailIsValid()) {
                if (Database.updateUser(this.voornaam.getText(), this.tussenvoegsel.getText(), this.achternaam.getText(), this.telefoon.getText(), this.email.getText(), loggedInUserId)) {
                    root = FXMLLoader.load(getClass().getResource("welkom-screen.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } else showAlert("ongeldige E-mail. Probeer opnieuw.");
        } else showAlert("hebt geen permissie om hier the zijn!");
    }

    private boolean emailIsValid() {
        Pattern p = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher m = p.matcher(email.getText());

        // Check if the email matches the pattern
        return m.matches();
    }

    // private boolean passwordIsSafe() {}

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
