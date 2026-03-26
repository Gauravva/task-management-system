package com.taskmanager.tms.auth.imp_class;

import com.taskmanager.tms.auth.interfaces.AuthService;
import com.taskmanager.tms.config.JwtUtil;
import com.taskmanager.tms.constants.TmsContants;
import com.taskmanager.tms.entity.User;
import com.taskmanager.tms.exception.exceptions.ResourceNotFoundException;
import com.taskmanager.tms.login.dto.request.LoginRequest;
import com.taskmanager.tms.login.dto.response.LoginResponse;
import com.taskmanager.tms.mapper.user_mapper.UserMapper;
import com.taskmanager.tms.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthOperation implements AuthService {

 private final UserRepo userRepo;
 private final PasswordEncoder passwordEncoder;
 private final JwtUtil jwtUtil;
 private final UserMapper userMapper;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
       User user = userRepo.findByEmail(loginRequest.getEmail()).
                            orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials"));

       if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
           throw new ResourceNotFoundException("Invalid password");
       }
       String token = jwtUtil.generateToken(user);
       return LoginResponse.builder()
               .accessToken(token)
               .tokenType(TmsContants.BEARER)
               .userResponse(userMapper.toResponse(user))
               .build();
    }
}
