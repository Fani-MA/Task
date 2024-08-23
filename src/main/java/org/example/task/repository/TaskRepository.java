package org.example.task.repository;

import org.example.task.domain.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {


    @Query(
            value = """
                    select * from tasks t
                    where t.author_id = :authorId
                """,
            nativeQuery = true)
    Page<Task> findTaskByAuthor(Long authorId, Pageable pageable);

    @Query(
            value = """
                    select * from tasks t
                    where t.executor_id = :userId
                """,
            nativeQuery = true)
    List<Task> findTaskByExecutor(Long userId);
}
