package org.example.task.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ErrorDto projectNotFoundException(ProjectNotFoundException e){
        return ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(List.of(e.getMessage()))
                .build();
    }
}
