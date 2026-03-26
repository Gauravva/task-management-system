package com.taskmanager.tms.repository;

import com.taskmanager.tms.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findByStatus(String status);

    List<Task> findByUserId(Long user_id);

    List<Task> findByTitleContaining(String keyword);

    Optional<Task> findById(Long taskId);
}
