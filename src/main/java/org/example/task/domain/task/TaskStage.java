package org.example.task.domain.task;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.task.domain.project.Project;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "task_stage")
public class TaskStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "stage", length = 20)
    String status;

    @ToString.Exclude
    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_task_stage",
            joinColumns = @JoinColumn(name = "task_stage"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    List<Project> projects = new ArrayList<>();


    @Builder.Default
    @OneToMany(mappedBy = "status")
    List<Task> tasks = new ArrayList<>();
}


