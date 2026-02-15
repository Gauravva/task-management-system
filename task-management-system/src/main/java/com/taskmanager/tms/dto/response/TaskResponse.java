package com.taskmanager.tms.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class TaskResponse {

        private Long id;
        private String title;
        private String description;
        private String status;
        private LocalDate dueDate;

}
