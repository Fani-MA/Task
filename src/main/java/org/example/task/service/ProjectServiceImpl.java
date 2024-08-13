package org.example.task.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.task.domain.project.*;
import org.example.task.exeption.ProjectNotFoundException;
import org.example.task.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    ProjectDtoFactory projectDtoFactory;

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
                .projectTaskStages(project.getProjectTaskStages())
                .projectTasks(project.getProjectTasks())
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

}
