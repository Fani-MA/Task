package org.example.task.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtRequest implements Serializable {

    @Email(message = "Email must not be empty")
    String email;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 4, message = "Password need contain min 4 characters")
    @ToString.Exclude
    String password;
}
