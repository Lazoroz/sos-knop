package com.example.sosknop;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Overzicht {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLocation(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("location-screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToToevoeg(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("contact-toevoegen-screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void show() {
        int sessionId = sessionManager.getInstance().getLoggedInUserId();
        Database.showContact(sessionId);


    }



}
