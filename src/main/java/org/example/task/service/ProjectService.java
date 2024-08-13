package org.example.task.service;

import org.example.task.domain.project.ProjectDto;
import org.example.task.domain.project.ProjectResponse;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> findAllProjects();

    ProjectResponse getProject(Long id);

    String closeProject(Long projectId, Long userId);

    List<ProjectDto> findMyProjects(Long id);
}
