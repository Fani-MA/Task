package org.example.task.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.task.domain.project.Project;
import org.example.task.domain.task.Task;

import java.util.ArrayList;
import java.util.Collection;
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
    @JsonIgnore
    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @ManyToMany
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    Collection<Role> roles;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "projectAuthor", fetch = FetchType.LAZY)
    List<Project> projects = new ArrayList<>();

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    List<Task> organizeTasks = new ArrayList<>();

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "executor", fetch = FetchType.LAZY)
    List<Task> executorTasks = new ArrayList<>();

}
