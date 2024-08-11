package org.example.task.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.example.task.domain.project.Project;
import org.example.task.domain.task.Task;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_name")
    String username;

    @ToString.Exclude
    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @Builder.Default
    @OneToMany(mappedBy = "projectAuthor")
    List<Project> projects = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "author")
    List<Task> organizeTasks = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "executor")
    List<Task> executorTasks = new ArrayList<>();

}
