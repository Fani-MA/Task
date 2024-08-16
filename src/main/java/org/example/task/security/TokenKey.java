package org.example.task.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenKey extends Key {
    @Value("${jwt.secret}")
    String key;
}
