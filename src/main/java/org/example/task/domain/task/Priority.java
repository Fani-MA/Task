package org.example.task.domain.task;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "priority")
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String priority;

    @ToString.Exclude
    @OneToMany(mappedBy = "priority")
    List<Task> priorityTasks;

}
