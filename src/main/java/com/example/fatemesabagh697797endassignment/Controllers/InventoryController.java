package com.example.fatemesabagh697797endassignment.Controllers;

import com.example.fatemesabagh697797endassignment.Data.Database;
import com.example.fatemesabagh697797endassignment.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    @FXML
    private TableView<Product> inventoryTableView;
    private ObservableList<Product> products;
   // private ArrayList<Product>
    private Database database;
    @FXML
    private TextField productStockField;
    @FXML
    private TextField productNameField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField descriptionField;
    public InventoryController(Database d){
       database=d;
   }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        products = FXCollections.observableArrayList(database.getAllProducts());
        inventoryTableView.setItems(products);
    }
    @FXML
    private void handleAddProduct(ActionEvent event) {
        try{
        int stock = Integer.parseInt(productStockField.getText());
        String name = productNameField.getText();
        String category = categoryField.getText();
        double price = Double.parseDouble(priceField.getText());
        String description = descriptionField.getText();

        Product newProduct = new Product(stock,name, category, price, description);
        products.add(newProduct);

        database.addProduct(newProduct);

        productNameField.clear();
        categoryField.clear();
        priceField.clear();
        descriptionField.clear();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleEditProduct(ActionEvent event) {
        Product selectedProduct = inventoryTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            selectedProduct.setStock(Integer.parseInt((productStockField.getText())));
            selectedProduct.setName(productNameField.getText());
            selectedProduct.setCategory(categoryField.getText());
            selectedProduct.setPrice(Double.parseDouble(priceField.getText()));
            selectedProduct.setDescription(descriptionField.getText());

            database.updateProduct(selectedProduct);

            inventoryTableView.refresh();

            productStockField.clear();
            productNameField.clear();
            categoryField.clear();
            priceField.clear();
            descriptionField.clear();
        }
    }
    @FXML
    private void handleDeleteProduct(ActionEvent event) {
        Product selectedProduct = inventoryTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            products.remove(selectedProduct);
            database.deleteProduct(selectedProduct.getName());
        }
    }
}

