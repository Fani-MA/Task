package org.example.task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.task.domain.project.ProjectDto;
import org.example.task.domain.project.ProjectResponse;
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


    @Operation(summary = "Get project",description = "Get project to id",
            responses = {
                @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ProjectResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable("id") Long id){

        return ResponseEntity.ok(projectService.getProject(id));
    }

    @Operation(summary = "Get all project",description = "Get all active projects",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema( implementation = ProjectResponse.class))))
    })
    @GetMapping
    public ResponseEntity<?> findAllProjects(){
        return ResponseEntity.ok(projectService.findAllProjects());
    }


    @Operation(summary = "Close project",description = "Close project to id",
            responses = {
                @ApiResponse(responseCode = "202",
                    content = @Content(schema = @Schema(implementation = ProjectResponse.class)))
    },
            security = @SecurityRequirement(name = "Bearer JWT"))
    @PostMapping("/close/{id}/{userId}")
    public ResponseEntity<?> closeProject(@PathVariable("id") Long id, @PathVariable("userId") Long userId){
        return ResponseEntity.ok(projectService.closeProject(id, userId));
    }

    @Operation(summary = "Close project",description = "Close project to id",
        responses = {
            @ApiResponse(responseCode = "200",
                content = @Content(
                        array = @ArraySchema(
                                schema = @Schema( implementation = ProjectResponse.class))))
},
            security = @SecurityRequirement(name = "Bearer JWT"))
    @GetMapping("/myProjects/{id}")
    public ResponseEntity<?> findMyProjects(@PathVariable("id") Long id){
        return ResponseEntity.ok(projectService.findMyProjects(id));
    }

    @Operation(summary = "Close project",description = "Close project to id",
            responses = {
                    @ApiResponse(responseCode = "200")
            },
            security = @SecurityRequirement(name = "Bearer JWT"))
    @PostMapping("/{project_id}/stages")
    public ResponseEntity<?> addTaskStageInProject(@PathVariable("project_id") Long projectId, @RequestBody TaskStageRequest stage){
        return ResponseEntity.ok(projectService.addNewStageToProject(projectId, stage.getStage()));
    }
}
