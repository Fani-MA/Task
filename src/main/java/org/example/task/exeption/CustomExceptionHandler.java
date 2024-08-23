package org.example.task.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ErrorDto projectNotFoundException(ProjectNotFoundException e){
        return new ErrorDto(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
