package com.example.conductor.service;

import com.example.conductor.model.Product;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface UserService {

    void insertData(String email, String status, Integer statusCode);

    List<Product> getProducts();
}
