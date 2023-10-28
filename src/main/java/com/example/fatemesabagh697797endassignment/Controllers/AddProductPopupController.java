package com.example.fatemesabagh697797endassignment.Controllers;

import com.example.fatemesabagh697797endassignment.Data.Database;
import com.example.fatemesabagh697797endassignment.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductPopupController implements Initializable {

    @FXML private TableView<Product> inventoryTableView;

    @FXML private TextField productQuantityField;
    private ObservableList<Product> productList ;
    private Product selectedProduct;
    private int quantity;
    Database database;
    private OrderController orderController;
    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        database = new Database();
        productList = FXCollections.observableArrayList(database.getAllProducts());
        inventoryTableView.setItems(productList);
    }

    public Product getProduct() {
        return selectedProduct;
    }

    @FXML
    private void addToOrder() {
        selectedProduct = inventoryTableView.getSelectionModel().getSelectedItem();
        try {
            quantity = Integer.parseInt(productQuantityField.getText());
        } catch (NumberFormatException e) {
            quantity = 0; // Handle invalid input
        }

        if (quantity <= selectedProduct.getStock()) {
            Product p = new Product(quantity,selectedProduct.getName(),selectedProduct.getCategory(),selectedProduct.getPrice(),selectedProduct.getDescription());
            orderController.addSelectedProduct(p);
        }

        Stage stage = (Stage) productQuantityField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) productQuantityField.getScene().getWindow();
        stage.close();
    }

}
