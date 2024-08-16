package org.example.task.repository;

import org.example.task.domain.task.TaskStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStageRepository extends JpaRepository<TaskStage, Long> {

    boolean existsTaskStageByStatus(String stage);

    TaskStage findByStatus(String status);

}
