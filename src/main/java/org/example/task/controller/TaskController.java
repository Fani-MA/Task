package org.example.task.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.task.service.TaskService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    TaskService taskService;

    @GetMapping("/author/{id}")
    public ResponseEntity<?> findAllTaskByUser(@PathVariable("id") Long id,
                                               @ParameterObject Pageable pageable
    ){

        return ResponseEntity.ok(taskService.getAuthorTasks(id, pageable));
    }
}
