//package com.example.conductor;
//import org.springframework.http.*;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//public class TestController {
//
//    @GetMapping("/test")
//    public static void main(String[] args) {
//        // 创建RestTemplate实例
//        RestTemplate restTemplate = new RestTemplate();
//
//        // 设置请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // 创建ObjectMapper实例，用于将对象转换为JSON字符串
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // 创建请求体
////        Map<String, String> requestBody = new HashMap<>();
////        requestBody.put("email", "example@gmail.com");
////        requestBody.put("password", "123456");
//        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
////        requestBody.add("email", "example@gmail.com");
////        requestBody.add("password", "123456");
//
//        // 创建param2的JSON对象
//        Map<String, String> param2 = new HashMap<>();
//        param2.put("password", "123456");
//        param2.put("item", "NNS");
//
//        try {
//            // 将param2转换为JSON字符串
//            String param2Json = objectMapper.writeValueAsString(param2);
//            requestBody.add("Data", param2Json);
//            System.out.println(requestBody);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return; // 如果转换失败，退出程序
//        }
//
//        // 将请求头和请求体封装成HttpEntity
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
////        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        // API URL
//        String apiUrl = "http://localhost:8080/users/login";
//
//        try {
//            // 发送POST请求并接收响应
//            ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
//
//            // 获取响应状态码
//            HttpStatusCode statusCode = responseEntity.getStatusCode();
//            System.out.println("Response Status Code: " + statusCode);
//
//            // 获取响应体
//            String responseBody = responseEntity.getBody();
//            System.out.println("Response Body: " + responseBody);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

package com.example.conductor;

import com.example.conductor.model.Product;
import com.example.conductor.service.UserService;
import com.example.conductor.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.spec.NamedParameterSpec;
import java.util.*;

@RestController
public class TestController {

    @Autowired
    private UserService userService;


//    public static void main(String[] args) {
    @GetMapping("/test")
    public void test() {

        // 创建 RestTemplate 实例
        RestTemplate restTemplate = new RestTemplate();

//        UserServiceImpl userService = new UserServiceImpl();

        // 设置请求头为 application/Json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String email = "zengalier5@gmail.com";

//        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, String> requestBody = new HashMap<>();

        requestBody.put("email", "zengalier5@gmail.com");
        requestBody.put("password", "123456Zx");

        System.out.println(requestBody);

        // API URL
        String apiUrl = "http://localhost:8080/users/login";  // 请替换为您实际的 API URL

        try {

            // 将请求体和请求头封装成 HttpEntity
            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            // 发送 POST 请求并接收响应
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

            // 获取响应状态码
            HttpStatusCode statusCode = responseEntity.getStatusCode();
            System.out.println("Response Status Code: " + statusCode);

            // 获取响应体
            String responseBody = responseEntity.getBody();
            System.out.println("Response Body: " + responseBody);

            int statusCode1 = responseEntity.getStatusCodeValue();

            //獲得fail/true
            String status = (statusCode==HttpStatus.OK) ? "TRUE" : "FALSE";
            System.out.println(status);

            String status1 = (statusCode1==200) ? "TRUE" : "FALSE";

            userService.insertData(email, status1, statusCode1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> productList = userService.getProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }
}

