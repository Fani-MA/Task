package org.example.task.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.task.domain.task.Task;
import org.example.task.domain.task.TaskResponse;
import org.example.task.domain.task.TaskResponseFactory;
import org.example.task.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskServiceImpl implements TaskService{

    TaskRepository taskRepository;
    TaskResponseFactory taskResponseFactory;
    //todo Make dto for response

    @Transactional(readOnly = true)
    public List<TaskResponse> getAuthorTasks(Long authorId){
        return taskRepository.findTaskByAuthor(authorId).stream().map(taskResponseFactory::createResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<Task> getExecutorTasks(Long userId){
        return taskRepository.findTaskByExecutor(userId);
    }

    
}
