package org.example.task.security;

import lombok.Data;

@Data
public class JwtRequest {
    String username;

    String email;

    String password;

}
