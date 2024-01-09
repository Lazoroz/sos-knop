package com.example.sosknop;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Location {

        @FXML
        private WebView mapView;

        public void initialize() {
            WebEngine webEngine = mapView.getEngine();

            try {
                webEngine.load("https://www.google.com/maps/embed/v1/place?key=AIzaSyAwofV1R-Ce2IlRZWzE6TJ28Q4CDOfRQdI");


            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception (log or display an error message)
            }
        }
}