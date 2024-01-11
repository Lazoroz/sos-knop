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

        String apiKey = "AIzaSyAwofV1R-Ce2IlRZWzE6TJ28Q4CDOfRQdI";

        String html = "<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2452.694948138796!2d4.3213990769902075!3d52.06707796966332!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47c5b6e175fe3619%3A0x9d1994a880751d7a!2sDe%20Haagse%20Hogeschool!5e0!3m2!1snl!2snl!4v1704895920720!5m2!1snl!2snl\" width=\"380\" height=\"525\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>";
        html = html.replace("</iframe>", "&key=" + apiKey + " frameborder='0' style='border:0' aria-hidden='false' tabindex='0'></iframe>");
        webEngine.loadContent(html);

    }

}

