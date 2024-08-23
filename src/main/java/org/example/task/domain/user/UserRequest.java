package org.example.task.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequest {

    @NotBlank(message = "Username must not be empty")
    @Size(min = 4, message = "Min length username is 4 characters")
    String username;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 4, message = "Min length password is 4 characters")
    String password;

    @Email(message = "Uncorrected email")
    String email;

}
