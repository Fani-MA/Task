package org.example.task.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.task.domain.project.*;
import org.example.task.domain.task.TaskStage;
import org.example.task.domain.task.TaskStageDto;
import org.example.task.domain.task.TaskStageDtoFactory;
import org.example.task.exeption.ProjectNotFoundException;
import org.example.task.repository.ProjectRepository;
import org.example.task.repository.TaskStageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    ProjectDtoFactory projectDtoFactory;
    TaskStageDtoFactory taskStageDtoFactory;
    TaskStageRepository taskStageRepository;

    @Transactional(readOnly = true)
    public List<ProjectDto> findAllProjects(){
        List<ProjectDto> projectDtoList = projectRepository.findAllByState(ProjectState.ACTIVE).stream()
                .map(projectDtoFactory::makeProjectDto)
                .collect(Collectors.toList());

        return projectDtoList;
    }


    @Transactional(readOnly = true)
    public ProjectResponse getProject(Long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(
                        ()-> new ProjectNotFoundException("Not found this project"));

        ProjectResponse response = ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .projectAuthor(project.getProjectAuthor().getUsername())
                .projectTaskStages(project.getProjectTaskStages().stream()
                        .map(taskStageDtoFactory::makeTaskStageDto).toList())
                .build();

        return response;
    }

    @Transactional
    public String closeProject(Long projectId, Long userId){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(
                        () -> new  ProjectNotFoundException("Not found this project to change state"));

        if(project.getProjectAuthor().getId().equals(userId)){
            project.setState(ProjectState.CLOSED);
            projectRepository.save(project);
        }

        return String.format("Project %d is closed", projectId);
    }


    @Transactional(readOnly = true)
    public List<ProjectDto> findMyProjects(Long id){
        List<ProjectDto> myProjects = projectRepository
                .findProjectsByProjectAuthor(id)
                .stream()
                .map(projectDtoFactory::makeProjectDto).toList();

        return myProjects;
    }

    @Transactional
    public String addNewStageToProject(Long projectId, String stage){

        Project project = projectRepository.findById(projectId)
                .orElseThrow(
                        () -> new ProjectNotFoundException("Not project for add new stage"));
        if(!taskStageRepository.existsTaskStageByStatus(stage)){
            taskStageRepository.save(TaskStage.builder()
                    .status(stage)
                    .projects(Set.of(project))
                    .build());
        } else {
            project.getProjectTaskStages().add(taskStageRepository.findByStatus(stage));
        }


        return String.format("Created new column: %s", stage);
    }


}
