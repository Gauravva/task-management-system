package com.taskmanager.tms.validator;

import com.taskmanager.tms.exception.exceptions.EmailAlreadyExistsException;
import com.taskmanager.tms.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepo userRepo;

    public void validateEmailUniqueness(String email) {

        if (userRepo.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }

}
