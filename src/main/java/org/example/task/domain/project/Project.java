package org.example.task.domain.project;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.task.domain.task.Task;
import org.example.task.domain.task.TaskStage;
import org.example.task.domain.user.User;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "state")
    ProjectState state = ProjectState.ACTIVE;

    @Builder.Default
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    OffsetDateTime createdAt = OffsetDateTime.now();

    @ManyToOne
    @JoinColumn(name = "author_id")
    User projectAuthor;

    @ToString.Exclude
    @Builder.Default
    @ManyToMany(mappedBy = "projects")
    List<TaskStage> projectTaskStages = new ArrayList<>();

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "project")
    List<Task> projectTasks = new ArrayList<>();


}
