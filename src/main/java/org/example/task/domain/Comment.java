package org.example.task.domain;


import jakarta.persistence.*;
import lombok.*;
import org.example.task.domain.task.Task;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "message")
    String message;

    @ManyToOne
    @JoinColumn(name = "task_id")
    Task task;

}
