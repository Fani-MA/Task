package org.example.task.service;

import org.example.task.domain.project.ProjectDto;
import org.example.task.domain.project.ProjectDtoFactory;
import org.example.task.domain.project.ProjectResponse;
import org.example.task.repository.ProjectRepository;

import java.util.List;

public interface ProjectService {

    public List<ProjectDto> findAllProjects();

    public ProjectResponse getProject(Long id);

    public String closeProject(Long projectId, Long userId);

    public List<ProjectDto> findMyProjects(Long id);
}
