package com.taskmanager.tms.auth.authservice;

import com.taskmanager.tms.entity.User;
import com.taskmanager.tms.repository.UserRepo;
import com.taskmanager.tms.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationService {

        private final UserRepo userRepo;

        public User getLoggedInUser() {
            String email = SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName();

            return userRepo.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        public void checkUserOwnership(Long requestedUserId) {

            User loggedInUser = getLoggedInUser();

            if (!loggedInUser.getRole().equals(Role.ADMIN) &&
                    !loggedInUser.getId().equals(requestedUserId)) {

                throw new AccessDeniedException("Access denied");
            }
        }

}
