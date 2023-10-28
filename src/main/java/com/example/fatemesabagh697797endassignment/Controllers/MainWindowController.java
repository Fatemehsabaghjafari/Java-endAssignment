package com.example.fatemesabagh697797endassignment.Controllers;
import com.example.fatemesabagh697797endassignment.Data.Database;
import com.example.fatemesabagh697797endassignment.Model.User;
import com.example.fatemesabagh697797endassignment.Model.UserRole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class MainWindowController{
    @FXML
    private Button DashBtn;
    @FXML
    private Button OrderBtn;
    @FXML
    private Button InventoryBtn;
    @FXML
    private Button HistoryBtn;

    @FXML
    HBox layout;
    @FXML
    private Label username;
    @FXML
    private Label role;
    private User user;
    private Database database;
    private Scene currentScene;
    public void setDatabase(Database database) {
        this.database = database;
    }
    public void setUser(User user) {
        this.user = user;
        try {
           validUserRole();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
    public void validUserRole() {
        try {
            UserRole userRole = user.getRole();

            DashBtn.setDisable(false);
            OrderBtn.setDisable(false);
            InventoryBtn.setDisable(false);
            HistoryBtn.setDisable(false);

            switch (userRole) {
                case SALES:
                    InventoryBtn.setDisable(true);
                    break;
                case MANAGER:
                    OrderBtn.setDisable(true);
                    break;
                default:
                    // Handle any other roles if necessary
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void welcomeUser(String userName, UserRole userRole) {
        username.setText("Welcome " + userName + "!");
        role.setText("Your role is: " + userRole);
    }
    public void loadScene(String name, Object controller) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(name));
            fxmlLoader.setController(controller);
            Scene newScene = new Scene(fxmlLoader.load());

            if (currentScene != null) {
                layout.getChildren().remove(currentScene.getRoot());
            }

            layout.getChildren().add(newScene.getRoot());

            currentScene = newScene;
            username.setText("");
            role.setText("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void OnDashboardButtonClick(ActionEvent event) {
        loadScene("Dashboard.fxml", new DashboardController(user));
    }
    public void OnOrderButtonClick(ActionEvent event) {
        loadScene("Create-order.fxml", new OrderController(database));
    }
    public void OnInventoryButtonClick(ActionEvent event) {
        loadScene("Inventory.fxml", new InventoryController(database));
    }
    public void OnHistoryButtonClick(ActionEvent event) {
        loadScene("History.fxml", new HistoryController(database));
    }


}