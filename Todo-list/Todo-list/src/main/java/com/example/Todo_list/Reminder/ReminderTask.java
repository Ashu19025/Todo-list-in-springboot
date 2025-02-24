package com.example.Todo_list.Reminder;

import com.example.Todo_list.Model.Task;
import com.example.Todo_list.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Component
public class ReminderTask {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 60000)
    public void sendReminder(){

        Instant instantNow = Instant.now();
        LocalDateTime now = LocalDateTime.ofInstant(instantNow, ZoneId.systemDefault());
        System.out.println("Current time:"+ now);
        List<Task> upcomingTasks = taskRepository.findByDueDateBeforeAndCompletedFalseAndReminderSentFalse(now.plusHours(1));
     for (Task task:upcomingTasks){
         System.out.println("Reminder: Task '" + task.getTitle() + "' is due soon!");

         task.setReminderSent(true);
         taskRepository.save(task);
     }
    }
}
