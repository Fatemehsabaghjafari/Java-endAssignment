package com.example.fatemesabagh697797endassignment.Controllers;

import com.example.fatemesabagh697797endassignment.Data.Database;
import com.example.fatemesabagh697797endassignment.Model.Order;
import com.example.fatemesabagh697797endassignment.Model.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    @FXML
    private TableView<Order> orderTableView;
    @FXML
    private TableView<Product> productPerOrderTableView;
    private Database database;
    private ObservableList<Order> orders;
    private ObservableList<Product> orderProducts;
    private Order selectedOrder;
    public HistoryController(Database database){
        this.database = database;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orders = FXCollections.observableArrayList(database.getAllOrders());
        orderTableView.setItems(orders);
        orderTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
            @Override
            public void changed(ObservableValue<? extends Order> observableValue,
                                Order oldOrder, Order newOrder) {
                if (newOrder != null) {
                    List<Product> productList = newOrder.getProducts();
                    orderProducts = FXCollections.observableArrayList(productList); // Convert to ObservableList
                    productPerOrderTableView.setItems(orderProducts);
                }
            }

        });
    }
}
