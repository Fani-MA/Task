package org.example.task.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ErrorDto projectNotFoundException(ProjectNotFoundException e){
//        return ErrorDto.builder()
//                .time(new Date())
//                .status(HttpStatus.BAD_REQUEST)
//                .message(e.getMessage())
//                .build();
        return new ErrorDto(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
