package com.example.sosknop;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Wachtwoord {
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

    public void updateWachtwoord(ActionEvent event) throws IOException {
    }
}