package org.example.task.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.task.domain.project.Project;
import org.example.task.domain.project.ProjectDto;
import org.example.task.repository.ProjectRepository;
import org.example.task.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/projects")
public class ProjectController {

    ProjectRepository projectRepository;
    ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable("id") Long id){
        ProjectDto res;
         if(projectRepository.findById(id).isPresent()){
             Project project = projectRepository.getReferenceById(id);
             res = ProjectDto.builder()
                     .id(project.getId())
                     .title(project.getTitle())
                     .state(project.getState())
                     .description(project.getDescription())
                     .dateTime(project.getCreatedAt())
                     .build();

         } else {
             res = null;
         }



        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @GetMapping
    public ResponseEntity<?> findAllProjects(){


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.findAllProjects());
    }


}
