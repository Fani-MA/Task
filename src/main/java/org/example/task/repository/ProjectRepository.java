package org.example.task.repository;

import lombok.NonNull;
import org.example.task.domain.project.Project;
import org.example.task.domain.project.ProjectState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findById(@NonNull  Long id);

    List<Project> findAllByState(ProjectState state);


    @Query(value =
        """
            select * from projects p
            where p.author_id = :id
        """,
            nativeQuery = true
    )
    List<Project> findProjectsByProjectAuthor(Long id);
}
