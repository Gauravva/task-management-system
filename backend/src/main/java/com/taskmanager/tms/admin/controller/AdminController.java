package com.taskmanager.tms.admin.controller;

import com.taskmanager.tms.dto.response.TaskResponse;
import com.taskmanager.tms.dto.response.UserResponse;
import com.taskmanager.tms.service.TaskService;
import com.taskmanager.tms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

        private final UserService userService;
        private final TaskService taskService;

        @GetMapping("/users")
        public List<UserResponse> getAllUsers() {
            return userService.getAllUsers();
        }

        @DeleteMapping("/users/{id}")
        public UserResponse deleteUser(@PathVariable Long id) {
           return userService.deleteUser(id);
        }

        @GetMapping("/task")
        public List<TaskResponse> fetchAllTask() {
            return taskService.fetchAllTasksForAdmin();
        }

}
