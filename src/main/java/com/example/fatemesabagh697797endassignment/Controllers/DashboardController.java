package com.example.fatemesabagh697797endassignment.Controllers;

import com.example.fatemesabagh697797endassignment.Model.User;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Label usernameLabel;
    @FXML
    private Label roleLabel;
    @FXML
    private Label dateTimeLabel;
    private User user;
    public DashboardController(User user) {
       this.user = user;
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        updateUserInfo();
        updateDateTime();
    }
    private void updateUserInfo() {
        if (user != null) {
            usernameLabel.setText("Welcome, " + user.getUsername() + "!");
            roleLabel.setText("Role: " + user.getRole().toString());
        } else {
            usernameLabel.setText("");
        }
    }
    private void updateDateTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        event -> {
                            LocalDateTime now = LocalDateTime.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                            String formatDateTime = now.format(formatter);
                            dateTimeLabel.setText("It is now: " + formatDateTime);
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
