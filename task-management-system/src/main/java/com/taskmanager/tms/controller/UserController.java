package com.taskmanager.tms.controller;

import com.taskmanager.tms.dto.request.UserRequest;
import com.taskmanager.tms.dto.response.UserResponse;
import com.taskmanager.tms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public UserResponse fetchProfile() {
        return userService.fetchUser();
    }

    @PutMapping("/user/{userId}")
    public UserResponse updateUser(@PathVariable Long userId,
                                   @Valid @RequestBody UserRequest userRequest) {
        return userService.updateUser(userId,userRequest);
    }

}
