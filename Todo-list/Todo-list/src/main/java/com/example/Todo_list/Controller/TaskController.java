package com.example.Todo_list.Controller;


import com.example.Todo_list.Model.Task;
import com.example.Todo_list.Repository.TaskRepository;
import com.example.Todo_list.Services.Taskservices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;



@Controller
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private Taskservices taskService;
    @Autowired
    private TaskRepository taskRepository;
    @GetMapping("/upcoming")
    public ResponseEntity<List<Task>> getUpcomingTasks() {
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        return ResponseEntity.ok(taskService.findUpcomingTasks(now));
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


}
