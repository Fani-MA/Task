package org.example.task.service;

import org.example.task.domain.task.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getAuthorTasks(Long userId);


}
