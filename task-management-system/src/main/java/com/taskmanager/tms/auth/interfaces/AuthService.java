package com.taskmanager.tms.auth.interfaces;

import com.taskmanager.tms.login.dto.request.LoginRequest;
import com.taskmanager.tms.login.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);
}
