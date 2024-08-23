package org.example.task.service;

import org.example.task.domain.task.TaskResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getAuthorTasks(Long userId, Pageable pageable);


}
