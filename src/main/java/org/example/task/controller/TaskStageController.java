package org.example.task.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stages")
public class TaskStageController {



    @PatchMapping("/create/{project_id}")
    ResponseEntity<?> createNewStages(@PathVariable("project_id") Long projectId){
        //todo обдумать необходимен ли данный функционал?
//        todo усли могу реализовать ручки в ProjectController

        return null;
    }




}
