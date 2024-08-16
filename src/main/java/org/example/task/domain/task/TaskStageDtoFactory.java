package org.example.task.domain.task;

import org.springframework.stereotype.Component;

@Component
public class TaskStageDtoFactory {

    public TaskStageDto makeTaskStageDto(TaskStage taskStage){
        return TaskStageDto.builder()
                .stage(taskStage.getStatus())
                .stageTasks(taskStage.getTasks().stream()
                        .map(task -> TaskDto.builder()
                                .id(task.getId())
                                .author(task.getAuthor().getUsername())
                                .executor(task.getExecutor().getUsername())
                                .priority(task.getTaskPriority())
                                .startTime(task.getStartTime())
                                .endTime(task.getEndTime())
                                .build()
                        ).toList())
                .build();

    }
}
