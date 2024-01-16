package com.example.sosknop;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Location {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLocation(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("location-screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToOverzicht(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("contact-overzicht-screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToWelkom(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("welkom-screen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

   /* private WebView mapView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine webEngine = mapView.getEngine();

        String apiKey = "AIzaSyAwofV1R-Ce2IlRZWzE6TJ28Q4CDOfRQdI";

        String html = "<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2452.694948138796!2d4.3213990769902075!3d52.06707796966332!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47c5b6e175fe3619%3A0x9d1994a880751d7a!2sDe%20Haagse%20Hogeschool!5e0!3m2!1snl!2snl!4v1704895920720!5m2!1snl!2snl\" width=\"380\" height=\"525\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>";
        html = html.replace("</iframe>", "&key=" + apiKey + " frameborder='0' style='border:0' aria-hidden='false' tabindex='0'></iframe>");
        webEngine.loadContent(html);

    }

}

     */

