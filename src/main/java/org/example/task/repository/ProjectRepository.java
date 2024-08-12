package org.example.task.repository;

import lombok.NonNull;
import org.example.task.domain.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


//
//    @Query("""
//            select
//            from Project p
//            where p.id = :id
//""")
    Optional<Project> findById(@NonNull  Long id);
//            p.id, p.title, p.state, p.description ,p.createdAt, p.projectAuthor
}
