package com.taskmanager.tms.service;

import com.taskmanager.tms.auth.authservice.AuthorizationService;
import com.taskmanager.tms.dto.request.TaskRequest;
import com.taskmanager.tms.dto.response.TaskResponse;
import com.taskmanager.tms.entity.Task;
import com.taskmanager.tms.entity.User;
import com.taskmanager.tms.exception.exceptions.ResourceNotFoundException;
import com.taskmanager.tms.mapper.task_mapper.TaskMapper;
import com.taskmanager.tms.repository.TaskRepo;
import com.taskmanager.tms.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final AuthorizationService authorizationService;
    private final TaskMapper taskMapper;

    public List<TaskResponse> createTask(List<TaskRequest> requests) {
        User loggedInUser = authorizationService.getLoggedInUser();

        List<Task> tasks = requests.stream()
                .map(request -> {
                    Task task = taskMapper.toEntity(request);
                    task.setUser(loggedInUser);
                    return task;
                })
                .toList();
        List<Task> savedTasks = taskRepo.saveAll(tasks);
        return savedTasks.stream()
                .map(taskMapper::toResponse)
                .toList();
    }


    public List<TaskResponse> fetchAllTask() {
        User loggedInUser = authorizationService.getLoggedInUser();
        return taskRepo.findByUserId(loggedInUser.getId())
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    public List<TaskResponse> fetchAllTasksForAdmin() {
        return taskRepo.findAll()
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }


    public TaskResponse fetchTask(Long taskId) {
        Task task = getTaskWithOwnershipCheck(taskId);
        return taskMapper.toResponse(task);
    }

    public TaskResponse updateTask(Long taskId, TaskRequest request) {
        Task task = getTaskWithOwnershipCheck(taskId);
        taskMapper.updateEntity(task, request);
        return taskMapper.toResponse(taskRepo.save(task));
    }

    public Task deleteTask(Long taskId) {
        Task task = getTaskWithOwnershipCheck(taskId);
        taskRepo.delete(task);
        return task;
    }

    private Task getTaskWithOwnershipCheck(Long taskId) {

        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        User loggedInUser = authorizationService.getLoggedInUser();

        if (!task.getUser().getId().equals(loggedInUser.getId())
                && loggedInUser.getRole() != Role.ADMIN) {

            throw new AccessDeniedException("Access denied");
        }

        return task;
    }
}


