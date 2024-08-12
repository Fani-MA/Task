package org.example.task.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.task.domain.project.ProjectDto;
import org.example.task.domain.project.ProjectDtoFactory;
import org.example.task.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectService {

    ProjectRepository projectRepository;
    ProjectDtoFactory projectDtoFactory;

    public List<ProjectDto> findAllProjects(){
        List<ProjectDto> projectDtoList = projectRepository.findAll().stream()
                .map(project -> projectDtoFactory.makeProjectDto(project))
                .collect(Collectors.toList());

        return projectDtoList;
    }
}
