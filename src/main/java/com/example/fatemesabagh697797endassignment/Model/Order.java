package com.example.fatemesabagh697797endassignment.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Order implements Serializable {
    private LocalDateTime dateTime;
    private String customerName;
    private List<Product> products;
    private double totalPrice;

    public Order(LocalDateTime dateTime, String customerName, List<Product> products) {
        this.dateTime = dateTime;
        this.customerName = customerName;
        this.products = products;
        calculateTotalPrice();
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void calculateTotalPrice() {
        totalPrice = products.stream().mapToDouble(Product::getPrice).sum();
    }
}
