package com.taskmanager.tms.login.dto.response;

import com.taskmanager.tms.dto.response.UserResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private String accessToken;
    private String tokenType;
    private UserResponse userResponse;


}
