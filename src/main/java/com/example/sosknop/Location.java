package com.example.sosknop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Location implements Initializable {

    @FXML
    private WebView mapView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine webEngine = mapView.getEngine();

        // Set the initial location (latitude, longitude)
        double latitude = 37.7749;
        double longitude = -122.4194;

        // Load Google Maps with the specified location
        webEngine.load("https://www.google.com/maps?q=" + latitude + "," + longitude);
    }
}
