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

import java.util.List;

@SpringBootApplication
public class ConductorApplication {

//    private final static Logger log = LoggerFactory.getLogger(ConductorApplication.class);
//
//    @Autowired
//    private Environment env;
//
//    private TaskClient taskClient;
//
    public static void main(String[] args) {
        SpringApplication.run(ConductorApplication.class, args);
    }

//    @Bean
//    public TaskRunnerConfigurer taskRunnerConfigurer(List<Worker> workerList){
//        log.info("Conductor Server URL: {}", env.getProperty("conductor.server.url"));
//        log.info("Starting workers: {}", workerList);
//        TaskRunnerConfigurer runnerConfigurer = new TaskRunnerConfigurer
//                .Builder(taskClient, workerList)
//                .withThreadCount(Math.max(1, workerList.size()))
//                .build();
//        runnerConfigurer.init();
//        return runnerConfigurer;
//    }
}
