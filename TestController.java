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

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
public class TestController {

    @GetMapping("/test")
    public static void main(String[] args) {
        // 创建 RestTemplate 实例
        RestTemplate restTemplate = new RestTemplate();

        // 设置请求头为 application/x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 构建 x-www-form-urlencoded 格式的请求体
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("pqId", "4");

        // 构建 item 参数的 JSON 数组
        String itemJson = "[{\"itemId\":\"3\",\"item\":\"NNR\"},{\"itemId\":\"4\",\"item\":\"NNo\"}]";
        requestBody.add("item", itemJson);

        System.out.println(requestBody);

        // API URL
        String apiUrl = "http://localhost:8080/users/login";  // 请替换为您实际的 API URL

        try {
            // 将请求体和请求头封装成 HttpEntity
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            // 发送 POST 请求并接收响应
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

            // 获取响应状态码
            HttpStatusCode statusCode = responseEntity.getStatusCode();
            System.out.println("Response Status Code: " + statusCode);

            // 获取响应体
            String responseBody = responseEntity.getBody();
            System.out.println("Response Body: " + responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//task
//{
//        "createTime": 1720619005584,
//        "updateTime": 1720619005584,
//        "createdBy": "zyy312706026.mg12@nycu.edu.tw",
//        "updatedBy": "zyy312706026.mg12@nycu.edu.tw",
//        "name": "HelloWorldTestWorker",
//        "description": "A simple Hello World Task",
//        "retryCount": 3,
//        "timeoutSeconds": 3600,
//        "inputKeys": [
//        "email",
//        "password"
//        ],
//        "outputKeys": [
//        "statusCode",
//        "responseBody"
//        ],
//        "timeoutPolicy": "TIME_OUT_WF",
//        "retryLogic": "FIXED",
//        "retryDelaySeconds": 60,
//        "responseTimeoutSeconds": 600,
//        "concurrentExecLimit": 0,
//        "inputTemplate": {},
//        "rateLimitPerFrequency": 0,
//        "rateLimitFrequencyInSeconds": 1,
//        "ownerEmail": "zyy312706026.mg12@nycu.edu.tw",
//        "pollTimeoutSeconds": 3600,
//        "backoffScaleFactor": 1
//        }


//workflow
//{
//        "createTime": 1720539309817,
//        "updateTime": 1720619256030,
//        "name": "HelloWorldTest",
//        "description": "A Hello World Test Workflow",
//        "version": 1,
//        "tasks": [
//        {
//        "name": "HelloWorldTestWorker",
//        "taskReferenceName": "HelloWorldTestWorker",
//        "inputParameters": {},
//        "type": "SIMPLE",
//        "decisionCases": {},
//        "defaultCase": [],
//        "forkTasks": [],
//        "startDelay": 0,
//        "joinOn": [],
//        "optional": false,
//        "defaultExclusiveJoinTask": [],
//        "asyncComplete": false,
//        "loopOver": [],
//        "onStateChange": {}
//        }
//        ],
//        "inputParameters": [],
//        "outputParameters": {
//        "statusCode": "${HelloWorldTestWorker.output.statusCode}",
//        "responseBody": "${HelloWorldTestWorker.output.responseBody}"
//        },
//        "failureWorkflow": "",
//        "schemaVersion": 2,
//        "restartable": true,
//        "workflowStatusListenerEnabled": false,
//        "ownerEmail": "zyy312706026.mg12@nycu.edu.tw",
//        "timeoutPolicy": "ALERT_ONLY",
//        "timeoutSeconds": 0,
//        "variables": {},
//        "inputTemplate": {}
//        }

