package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository repo;

    public TaskController(TaskRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Task> getTasks() {
        return repo.findAll();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return repo.save(task);
    }

    @PutMapping("/{id}")
    public Task markTaskCompleted(@PathVariable Long id) {
        Task task = repo.findById(id).orElseThrow();
        task.setCompleted(true);
        return repo.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
