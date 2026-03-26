package com.taskmanager.tms.service;

import com.taskmanager.tms.auth.authservice.AuthorizationService;
import com.taskmanager.tms.dto.request.UserRequest;
import com.taskmanager.tms.dto.response.UserResponse;
import com.taskmanager.tms.entity.User;
import com.taskmanager.tms.exception.exceptions.ResourceNotFoundException;
import com.taskmanager.tms.mapper.user_mapper.UserMapper;
import com.taskmanager.tms.repository.UserRepo;
import com.taskmanager.tms.role.Role;
import com.taskmanager.tms.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;
    private final AuthorizationService authorizationService;

    public UserResponse createUser(UserRequest userRequest) {
        userValidator.validateEmailUniqueness(userRequest.getEmail());
        User user = userMapper.toEntity(userRequest);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User savedUser =  userRepo.save(user);
        UserResponse response = userMapper.toResponse(savedUser);
        return response;
    }

    public UserResponse fetchUser() {
       User user =  authorizationService.getLoggedInUser();
       return userMapper.toResponse(user);
    }

    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        userRepo.findById(userId).orElseThrow(
               () -> new ResourceNotFoundException("user not found")
       );
       return userMapper.toResponse(userRepo.save(userMapper.toEntity(userRequest)));
    }

    public UserResponse deleteUser(Long userId) {
      User user = userRepo.findById(userId).orElseThrow(
              ()-> new ResourceNotFoundException("user not found")
      );
      userRepo.deleteById(userId);
      return userMapper.toResponse(user);
    }

    public List<UserResponse> getAllUsers() {
       List<User> users = userRepo.findAll();
        return users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

}
