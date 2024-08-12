package org.example.task.domain.project;


import org.springframework.stereotype.Component;

@Component
public class ProjectDtoFactory {


    public ProjectDto makeProjectDto(Project project){

        return ProjectDto.builder()
                .id(project.getId())
                .state(project.getState())
                .title(project.getTitle())
                .description(project.getDescription())
                .dateTime(project.getCreatedAt())
                .build();
    }
}
