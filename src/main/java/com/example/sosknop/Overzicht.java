package com.example.sosknop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class Overzicht {

    @FXML
    private TableView<Contacten> contactenTableView;
    @FXML
    private TableColumn<Contacten, String > naam;
    @FXML
    private TableColumn<Contacten, Integer > telefoon;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize() {
        try {
            naam.setCellValueFactory(cellData -> cellData.getValue().naamProperty());
            telefoon.setCellValueFactory(cellData -> cellData.getValue().telefoonProperty().asObject());

            // Load data from the database and populate the TableView
            contactenTableView.setItems(Database.showContact());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}


