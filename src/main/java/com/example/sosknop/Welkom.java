package com.example.sosknop;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Welkom {
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

    public void switchToBewerk(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profiel-bewerken-screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToWachtwoord(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("wactwoordVeranderen-screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToKlantservice(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("klantenservice-screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMain(ActionEvent event) throws IOException {
        sessionManager.getInstance().endSession();
        root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
