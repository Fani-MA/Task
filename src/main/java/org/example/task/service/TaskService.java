package org.example.task.service;

import org.example.task.domain.task.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAuthorTasks(Long userId);


}
