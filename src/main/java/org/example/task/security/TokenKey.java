package org.example.task.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class TokenKey  {
    @Value("${jwt.secret}")
    String key;
}
