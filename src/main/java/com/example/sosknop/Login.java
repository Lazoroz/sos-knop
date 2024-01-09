package com.example.sosknop;
import javafx.scene.control.TextField;
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



           // if (authenticateUser()) {
                    // Switch to the home screen
                    root = FXMLLoader.load(getClass().getResource("location-screen.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
              //  } else {
                // Display an error message for incorrect login info
             //   showAlert("Ongeldige inloggegevens. Probeer opnieuw.");
            //    }

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

   /* private boolean authenticateUser() {
        String enteredEmail = email.getText();
        String enteredPassword = wachtwoord.getText();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM users WHERE email = ? AND wachtwoord = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, enteredEmail);
                preparedStatement.setString(2, enteredPassword);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // User with entered email and password exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log or display an error message)
        }
        return false; // Default to false if an exception occurs
    }

    */

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /*
    //Database connection
    private Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:sqlite:your_database_name.db");
    }

     */
}