package org.example.task.domain.user;

import lombok.Data;

@Data
public class RegistrationUserDto {

    String username;
    String password;
    String confirmPassword;
    String email;
}