package org.example.task.domain.task;

import jakarta.persistence.*;
import lombok.*;
import org.example.task.domain.Comment;
import org.example.task.domain.project.Project;
import org.example.task.domain.user.User;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    User author;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    User executor;

    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    Priority priority;

    @Column(name = "created_at")
    @Builder.Default
    OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "start_time")
    OffsetDateTime startTime;

    @Column(name = "end_time")
    OffsetDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "status_id")
    TaskStage stage;

    @OneToMany(mappedBy = "task")
    List<Comment> comments = new ArrayList<>();


}
