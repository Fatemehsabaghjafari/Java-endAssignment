package com.example.fatemesabagh697797endassignment.Serializaton;

import com.example.fatemesabagh697797endassignment.Model.Order;
import com.example.fatemesabagh697797endassignment.Model.Product;
import com.example.fatemesabagh697797endassignment.Model.User;

import java.io.Serializable;
import java.util.List;

public class SerializeWrapper implements Serializable {
    public List<User> users;
    public List <Product> products;
    public List<Order> orders;
    public SerializeWrapper(List<User> users, List<Product> products, List<Order> orders){
        this.users = users;
        this.orders = orders;
        this.products = products;
    }
}
