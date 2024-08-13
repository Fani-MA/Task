package org.example.task.domain.project;

import lombok.Builder;
import lombok.Data;
import org.example.task.domain.task.Task;
import org.example.task.domain.task.TaskStage;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class ProjectResponse {
    Long id;
    String title;
    String description;
    String projectAuthor;
    Set<TaskStage> projectTaskStages;
    List<Task> projectTasks;
}
