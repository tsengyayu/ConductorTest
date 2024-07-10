package com.example.conductor.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.Map;

@Component
public class HelloWorldTestWorker implements Worker {

    @Override
    public String getTaskDefName() {
        return "hello_world_test";
    }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("email", "zengalier5@gmail.com");

        Map<String, String> param2 = new HashMap<>();
        param2.put("password", "123456Zx");
//        param2.put("item", "NNS");

        try {
            String param2Json = objectMapper.writeValueAsString(param2);
            requestBody.add("password", param2Json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            result.setStatus(TaskResult.Status.FAILED);
            return result;
        }

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        String apiUrl = "http://localhost:8080/users/login";

        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
            HttpStatusCode statusCode = responseEntity.getStatusCode();
            result.addOutputData("statusCode", statusCode);
            result.addOutputData("responseBody", responseEntity.getBody());
            result.setStatus(TaskResult.Status.COMPLETED);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(TaskResult.Status.FAILED);
        }

        return result;
    }
}
