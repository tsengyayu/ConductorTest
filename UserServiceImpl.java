package com.example.conductor.service.impl;

import com.example.conductor.model.Product;
import com.example.conductor.rowMapper.ProductRowMapper;
import com.example.conductor.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void insertData(String email, String status, Integer statusCode) {

        System.out.println("執行");


        //存入資料庫
        String sql = "INSERT INTO PMTest(customId, time, status, code) " +
                "VALUES (:email, :time, :status, :statusCode)";

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("time", new Date());
        map.put("status", status);
        map.put("statusCode", statusCode.toString());

        System.out.println(map);

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public List<Product> getProducts() {
//        String sql = "SELECT id,name,url,payload,response FROM PMSearch";
        String sql = "SELECT * FROM PMSearch";
        List<Product> productList = namedParameterJdbcTemplate.query(sql, new ProductRowMapper());

        for (Product userData : productList) {
            try {
                // 将 payload 字段转换为 JSON 对象
                JsonNode payloadJson = objectMapper.readTree(userData.getPayload());
                System.out.println("Payload JSON for ID " + userData.getId() + ": " + payloadJson.toPrettyString());

                // 将 response 字段转换为 JSON 对象
                JsonNode responseJson = objectMapper.readTree(userData.getResponse());
                System.out.println("Response JSON for ID " + userData.getId() + ": " + responseJson.toPrettyString());

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return productList;
    }
}
