package com.example.sosknop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public class Aanmeld {

        @FXML
        private TextField voornaam;

        @FXML
        private  TextField tussenvoegsels;

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
                        if (showUserAgreement()) {

                            // Show the user agreement
                            if (Database.newUser(
                                    this.voornaam.getText(),
                                    this.tussenvoegsels.getText(),
                                    this.achternaam.getText(),
                                    this.telefoon.getText(),
                                    this.email.getText(),
                                    this.wachtwoord.getText() )) {

                                root = FXMLLoader.load(getClass().getResource("login-screen.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            }
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
            Pattern p = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
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

        private boolean showUserAgreement() {
            DialogPane dialogPane = new DialogPane();
            dialogPane.setHeaderText("Gebruikersovereenkomst");

            VBox contentVBox = new VBox();

            contentVBox.getChildren().addAll(
                    new Label("SOS-knop Gebruikersovereenkomst\n" +
                    "\n" +
                    "Laatst bijgewerkt: 23 Januari 2024\n" +
                    "\n" +
                    "Welkom bij SOS-knop! Door onze app te gebruiken, stemt u in met en bent u gebonden aan de volgende voorwaarden. Lees deze voorwaarden zorgvuldig door. Hierbij gaat u ook akkoord met de eisen die wij hanteren volgens de AVG: ") ,
                            new Hyperlink("https://www.autoriteitpersoonsgegevens.nl/themas/financien/betaaldiensten/uitdrukkelijke-toestemming-vragen-als-betaaldienstverlener"),
                            new Label(

                    "1. Acceptatie van Voorwaarden\n" +
                    "Door toegang te krijgen tot of gebruik te maken van SOS-knop, gaat u akkoord met deze servicevoorwaarden, alle toepasselijke wetten en regelgeving. Als u het niet eens bent met een deel van deze voorwaarden, mag u de app niet gebruiken.\n" +
                    "\n" +
                    "2. Gebruikersregistratie\n" +
                    "Om bepaalde functies van de app te gebruiken, moet u mogelijk registreren en nauwkeurige en volledige informatie verstrekken. U bent als enige verantwoordelijk voor het vertrouwelijk houden van uw accountinformatie.\n" +
                    "\n" +
                    "3. Gebruikersgedrag\n" +
                    "U stemt ermee in geen activiteiten te ondernemen die SOS-knop kunnen schaden, verstoren of anderszins negatief kunnen beïnvloeden. Dit omvat onder andere ongeoorloofde toegang, verspreiding van schadelijke software en illegale activiteiten.\n" +
                    "\n" +
                    "4. Privacybeleid\n" +
                    "Wij respecteren uw privacy. Ons privacybeleid legt uit hoe we informatie over u verzamelen, gebruiken en openbaar maken. Door SOS-knop te gebruiken, gaat u akkoord met ons privacybeleid.\n" +
                    "\n" +
                    "5. Intellectueel Eigendom\n" +
                    "Alle inhoud en materialen die beschikbaar zijn in SOS-knop worden beschermd door toepasselijke intellectuele eigendomswetten. U stemt ermee in om onze inhoud niet te gebruiken, reproduceren, verspreiden of afgeleide werken te maken zonder onze uitdrukkelijke schriftelijke toestemming.\n" +
                    "\n" +
                    "6. Beëindiging\n" +
                    "Wij behouden het recht voor om uw account en toegang tot SOS-knop om welke reden dan ook te beëindigen of op te schorten, zonder voorafgaande kennisgeving.\n" +
                    "\n" +
                    "7. Disclaimer en Aansprakelijkheidsbeperking\n" +
                    "SOS-knop wordt geleverd 'zoals het is' zonder enige garanties, en wij wijzen alle aansprakelijkheid af voor schade voortvloeiend uit of verband houdend met uw gebruik van de app.\n" +
                    "\n" +
                    "8. Wijzigingen in de Voorwaarden\n" +
                    "We kunnen deze voorwaarden van tijd tot tijd bijwerken. U bent verantwoordelijk voor regelmatige controle van deze voorwaarden. Uw voortgezet gebruik van SOS-knop na het plaatsen van wijzigingen, houdt in dat u de aangepaste voorwaarden accepteert.\n" +
                    "\n" +
                    "9. Toepasselijk Recht\n" +
                    "Deze voorwaarden worden beheerst door en geïnterpreteerd volgens de wetten van uw rechtsgebied.\n" +
                    "\n" +
                    "Als u vragen heeft over deze voorwaarden, neem dan contact met ons op via sos-knop@info.nl of bel ons op 12345678.\n" +
                    "\n" +
                    "Dank u voor het gebruik van SOS-knop!\n\n")


            );

            CheckBox acceptCheckBox = new CheckBox("Ik heb de gebruikersovereenkomst gelezen en ga ermee akkoord.");
            contentVBox.getChildren().add(acceptCheckBox);

            ScrollPane scrollPane = new ScrollPane(contentVBox);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);

            dialogPane.setContent(scrollPane);

            dialogPane.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            Alert alert = new Alert(AlertType.NONE);
            alert.setDialogPane(dialogPane);

            // Show the dialog and wait for user input
            Optional<ButtonType> result = alert.showAndWait();

            // Return true if the user clicks "OK" and the checkbox is selected, false otherwise
            return result.isPresent() && result.get() == ButtonType.OK && acceptCheckBox.isSelected();
        }
    }