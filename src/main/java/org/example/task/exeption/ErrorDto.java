package org.example.task.exeption;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@Builder
public class ErrorDto{
    //        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        Date time;
        HttpStatus status;
        String message;



    public ErrorDto( HttpStatus status, String message){
        this.time = new Date();
        this.status = status;
        this.message = message;
    }
}
