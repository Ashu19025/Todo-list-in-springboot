package com.example.Todo_list.Repository;

import com.example.Todo_list.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByDueDateBeforeAndCompletedFalseAndReminderSentFalse(LocalDateTime dueDate);

}
