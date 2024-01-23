package com.example.sosknop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
        int loggedInUserId = sessionManager.getInstance().getLoggedInUserId();
        if (loggedInUserId != 0) {
            if (areFieldsFilled()) {
                if (showUserAgreement()) {
                    if (Database.newContact(this.voornaam.getText(), this.tussenvoegsels.getText(), this.achternaam.getText(), this.telefoonnummer.getText())) {
                        // Switch to the home screen
                        root = FXMLLoader.load(getClass().getResource("contact-overzicht-screen.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                }
            } else {
                // Display an error message or handle the validation failure
                showAlert("Vul alstublieft alle velden in.");
            }
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

    private boolean showUserAgreement() {
        DialogPane dialogPane = new DialogPane();
        dialogPane.setHeaderText("Contactovereenkomst");

        VBox contentVBox = new VBox();
        contentVBox.getChildren().addAll(
                new Label("SOS-knop Contactovereenkomst\n" +
                        "\n" +
                        "Laatst bijgewerkt: 23 Januari 2024\n" +
                        "\n" +
                        "Je hebt succesvol een contactpersoon toegevoegd. Een SMS wordt nu verstuurd naar de toegevoegde contactpersoon om hen op de hoogte te stellen.\n" +
                        "\n"
                        )
        );

        ScrollPane scrollPane = new ScrollPane(contentVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        CheckBox acceptCheckBox = new CheckBox("Ik heb de Contactovereenkomst gelezen en ga ermee akkoord.");

        // Add the checkbox to the dialog
        dialogPane.getChildren().add(acceptCheckBox);

        dialogPane.setContent(scrollPane);

        dialogPane.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);


        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setDialogPane(dialogPane);

        // Show the dialog and wait for user input
        Optional<ButtonType> result = alert.showAndWait();

        // Return true if the user clicks "OK" and the checkbox is selected, false otherwise
        return result.isPresent() && result.get() == ButtonType.OK && acceptCheckBox.isSelected();
    }
}
