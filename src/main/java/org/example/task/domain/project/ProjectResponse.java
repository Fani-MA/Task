package org.example.task.domain.project;

import lombok.Builder;
import lombok.Data;
import org.example.task.domain.task.Task;
import org.example.task.domain.task.TaskDto;
import org.example.task.domain.task.TaskStageDto;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class ProjectResponse {
    Long id;
    String title;
    String description;
    String projectAuthor;
    List<TaskStageDto> projectTaskStages;
//    List<TaskDto> projectTasks;
}
