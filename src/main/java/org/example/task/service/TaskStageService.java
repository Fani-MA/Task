package org.example.task.service;

import org.example.task.repository.TaskStageRepository;

public class TaskStageService {

    TaskStageRepository taskStageRepository;

//    @Transactional
//    void save(TaskStage taskStage){
//        if(!taskStageRepository.existsTaskStageByStatus(taskStage)){
//            taskStageRepository.save(taskStage);
//        } else {
//            throw new RuntimeException("This stage exists");
//        }
//    }
}
