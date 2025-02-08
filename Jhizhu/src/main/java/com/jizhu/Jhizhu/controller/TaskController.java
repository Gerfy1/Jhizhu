package com.jizhu.Jhizhu.controller;


import com.jizhu.Jhizhu.entity.Task;
import com.jizhu.Jhizhu.repository.TaskRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepositoty taskRepositoty;

    @GetMapping("/{userId}")
    public List<Task> getTasks(@PathVariable Long userId) {
        return taskRepositoty.findByUserId(userId);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepositoty.save(task);
    }
}
