package com.taskmanager.tms.controller;

import com.taskmanager.tms.dto.request.TaskRequest;
import com.taskmanager.tms.dto.response.TaskResponse;
import com.taskmanager.tms.entity.Task;
import com.taskmanager.tms.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/task")
    public List<TaskResponse> create(@RequestBody List<TaskRequest> taskRequests) {
        return taskService.createTask(taskRequests);
    }

    @GetMapping("/task")
    public List<TaskResponse> fetchAll() {
        return taskService.fetchAllTask();
    }

    @GetMapping("/task/{id}")
    public TaskResponse fetch(@PathVariable Long id) {
        return taskService.fetchTask(id);
    }

    @PutMapping("/task/{id}")
    public TaskResponse update( @PathVariable Long id,
                        @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest);
    }

    @DeleteMapping("/task/{id}")
    public Task delete(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

}
