package com.example.conductor.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiCheckWorker implements Worker {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getTaskDefName() {
        return "api_check_task";
    }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        String apiUrl = (String) task.getInputData().get("api_url");

        try {
            ApiResponse response = restTemplate.getForObject(apiUrl, ApiResponse.class);
            result.addOutputData("response", response);

            if (response != null && response.isSuccess()) {
                result.setStatus(TaskResult.Status.COMPLETED);
            } else {
                result.setStatus(TaskResult.Status.FAILED);
            }
        } catch (Exception e) {
            result.setStatus(TaskResult.Status.FAILED);
            result.addOutputData("error", e.getMessage());
        }

        return result;
    }

    // 定義ApiResponse類
    static class ApiResponse {
        private boolean success;
        // other fields and getters/setters

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }
}
