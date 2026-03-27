package com.taskmanager.tms.auth.controller;

import com.taskmanager.tms.auth.interfaces.AuthService;
import com.taskmanager.tms.dto.request.UserRequest;
import com.taskmanager.tms.dto.response.UserResponse;
import com.taskmanager.tms.login.dto.request.LoginRequest;
import com.taskmanager.tms.login.dto.response.LoginResponse;
import com.taskmanager.tms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }
}
