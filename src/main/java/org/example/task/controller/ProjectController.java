package org.example.task.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.task.domain.task.TaskStageRequest;
import org.example.task.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/projects")
public class ProjectController {

    ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable("id") Long id){

        return ResponseEntity.ok(projectService.getProject(id));
    }

    @GetMapping
    public ResponseEntity<?> findAllProjects(){

        return ResponseEntity.ok(projectService.findAllProjects());
    }


    @PostMapping("/close/{id}/{userId}")
    public ResponseEntity<?> closeProject(@PathVariable("id") Long id, @PathVariable("userId") Long userId){

        return ResponseEntity.ok(projectService.closeProject(id, userId));
    }

    @GetMapping("/myProjects/{id}")
    public ResponseEntity<?> findMyProjects(@PathVariable("id") Long id){

        return ResponseEntity.ok(projectService.findMyProjects(id));
    }

    @PostMapping("/{project_id}/stages")
    public ResponseEntity<?> addTaskStageInProject(@PathVariable("project_id") Long projectId, @RequestBody TaskStageRequest stage){

        return ResponseEntity.ok(projectService.addNewStageToProject(projectId, stage.getStage()));
    }
}
