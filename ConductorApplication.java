//package com.example.conductor;
//
//import com.netflix.conductor.client.worker.Worker;
//import io.orkes.conductor.client.TaskClient;
//import io.orkes.conductor.client.automator.TaskRunnerConfigurer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.core.env.Environment;
//
//import java.util.List;
//
//@SpringBootApplication
//public class ConductorApplication {
//
//    private final static Logger log = LoggerFactory.getLogger(ConductorApplication.class);
//
//    @Autowired
//    private Environment env;
//
//    public static void main(String[] args) {
//        SpringApplication.run(ConductorApplication.class, args);
//    }
//
//    public TaskRunnerConfigurer taskRunnerConfigurer(List<Worker> workerList, TaskClient taskClient){
//        log.info("conductor Server URL: {}", env.getProperty("conductor.server.url"));
//        log.info("Starting workers: {}", workerList);
//        TaskRunnerConfigurer runnerConfigurer = new TaskRunnerConfigurer
//                .Builder(taskClient, workerList)
//                .withThreadCount(Math.max(1, workerList.size()))
//                .build();
//        runnerConfigurer.init();
//        return runnerConfigurer;
//
//    }
//
//}


package com.example.conductor;

import com.netflix.conductor.client.worker.Worker;
import io.orkes.conductor.client.TaskClient;
import io.orkes.conductor.client.automator.TaskRunnerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class ConductorApplication {

    private final static Logger log = LoggerFactory.getLogger(ConductorApplication.class);

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(ConductorApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public TaskRunnerConfigurer taskRunnerConfigurer(List<Worker> workerList, TaskClient taskClient){
        log.info("conductor Server URL: {}", env.getProperty("conductor.server.url"));
        log.info("Starting workers: {}", workerList);
        TaskRunnerConfigurer runnerConfigurer = new TaskRunnerConfigurer
                .Builder(taskClient, workerList)
                .withThreadCount(Math.max(1, workerList.size()))
                .build();
        runnerConfigurer.init();
        return runnerConfigurer;
    }

}

//WorkFlow
//{
//        "name": "api_response_check_workflow",
//        "description": "Workflow to check API response success field",
//        "version": 1,
//        "tasks": [
//        {
//        "name": "api_check_task",
//        "taskReferenceName": "check_api_response",
//        "inputParameters": {
//        "api_url": "http://your-api-endpoint.com/api"
//        },
//        "type": "SIMPLE"
//        }
//        ],
//        "ownerEmail": "your_email@example.com"
//        }

//Task
//{
//        "name": "http_get_task",
//        "description": "A task to get response from API",
//        "retryCount": 3,
//        "timeoutSeconds": 60,
//        "inputKeys": [
//        "http_request"
//        ],
//        "type": "HTTP",
//        "httpRequest": {
//        "uri": "${http_request.uri}",
//        "method": "GET",
//        "headers": {},
//        "readTimeout": 5000
//        },
//        "responseTimeoutSeconds": 60,
//        "ownerEmail": "your_email@example.com"
//        }



