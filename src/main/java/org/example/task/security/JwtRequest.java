package org.example.task.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {
    String username;

//    String email;

    String password;

}
