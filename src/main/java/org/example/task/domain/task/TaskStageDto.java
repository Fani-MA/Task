package org.example.task.domain.task;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TaskStageDto {
    String stage;
    List<TaskDto> stageTasks;
}
