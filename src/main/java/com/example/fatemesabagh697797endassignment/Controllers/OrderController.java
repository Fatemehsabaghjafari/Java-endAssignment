package com.example.fatemesabagh697797endassignment.Controllers;

import com.example.fatemesabagh697797endassignment.Controllers.AddProductPopupController;
import com.example.fatemesabagh697797endassignment.Data.Database;
import com.example.fatemesabagh697797endassignment.Model.Order;
import com.example.fatemesabagh697797endassignment.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneNumberField;
    private ObservableList<Product> selectedProducts = FXCollections.observableArrayList();
    @FXML private TableView<Product> productTableView;

    private Database database;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productTableView.setItems(selectedProducts);

    }

    public OrderController(Database database) {
        this.database = database;
    }

    public void addSelectedProduct(Product product) {
        selectedProducts.add(product);
    }

    @FXML
    private void handleAddProduct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProductPopup.fxml"));
            Parent root = loader.load();

            AddProductPopupController controller = loader.getController();
            controller.setOrderController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCreateOrder(ActionEvent event) {
        try {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();

            LocalDateTime dateTime = LocalDateTime.now();
            Order order = new Order(dateTime, firstName + " " + lastName,new ArrayList<>(selectedProducts));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Order");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to create this order?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                database.addOrder(order);
                productTableView.setItems(FXCollections.observableArrayList());
                for (Product p : selectedProducts) {
                    int newStock = p.getStock();
                  database.updateStock(p, newStock);
                }

                firstNameField.clear();
                lastNameField.clear();
                emailField.clear();
                phoneNumberField.clear();
                selectedProducts.clear();
                productTableView.getItems().clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteProduct(ActionEvent event) {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            selectedProducts.remove(selectedProduct);
            productTableView.getItems().remove(selectedProduct);
        }
    }
}
