package org.example.task.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.task.domain.user.User;
import org.example.task.domain.user.UserDTO;
import org.example.task.domain.user.UserRequest;
import org.example.task.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    UserRepository userRepository;


    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userRepository.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest user){


        userRepository.save(User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .build());


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

}
