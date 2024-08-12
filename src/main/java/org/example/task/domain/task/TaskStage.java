package org.example.task.domain.task;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.task.domain.project.Project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @JsonBackReference
    @Builder.Default
    @ManyToMany(mappedBy = "projectTaskStages", fetch = FetchType.LAZY)
    Set<Project> projects = new HashSet<>();


    @Builder.Default
    @OneToMany(mappedBy = "stage",fetch = FetchType.LAZY)
    List<Task> tasks = new ArrayList<>();
}


