package com.taskmanager.tms.admin.initializer;

import com.taskmanager.tms.entity.User;
import com.taskmanager.tms.repository.UserRepo;
import com.taskmanager.tms.role.Role;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdmin() {

        if (userRepo.findByEmail("admin@test.com").isEmpty()) {

            User admin = new User();
            admin.setName("Super Admin");
            admin.setEmail("admin@test.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);

            userRepo.save(admin);
        }
    }
}
