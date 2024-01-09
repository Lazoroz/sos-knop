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

        // Replace these values with the actual latitude and longitude of your location
        double latitude = 37.7749;
        double longitude = -122.4194;

        String htmlContent = "<iframe width=\"100%\" height=\"50%\" frameborder=\"0\" style=\"border:0\" "
                + "src=\"https://www.google.com/maps/embed/v1/view?key=AIzaSyAwofV1R-Ce2IlRZWzE6TJ28Q4CDOfRQdI&center="
                + latitude + "," + longitude + "&zoom=15\" allowfullscreen></iframe>";

        webEngine.loadContent(htmlContent);
    }
}
