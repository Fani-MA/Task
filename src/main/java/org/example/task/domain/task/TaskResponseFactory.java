package org.example.task.domain.task;

import org.springframework.stereotype.Component;

@Component
public class TaskResponseFactory {


    public TaskResponse createResponse(Task task){

    return TaskResponse.builder()
                .author(task.getAuthor().getUsername())
                .executor(task.getExecutor() != null ? task.getExecutor().getUsername() : "Не назначен")
                .priority(task.getTaskPriority().getLevel())
                .startTime(task.getStartTime())
                .endTime(task.getEndTime())
                .build();
    }
}
