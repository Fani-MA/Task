package org.example.task.exeption;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
public class ErrorDto{
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        Date time;
        HttpStatus status;
        String message;



    public ErrorDto( HttpStatus status, String message){
        this.time = new Date();
        this.status = status;
        this.message = message;
    }
}
