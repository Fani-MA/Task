package org.example.task.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.task.exeption.ErrorDto;
import org.example.task.security.JwtRequest;
import org.example.task.security.JwtResponse;
import org.example.task.security.JwtTokenUtils;
import org.example.task.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
//@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {


    UserServiceImpl userService;
    JwtTokenUtils  jwtTokenUtils;
    AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createToken(
            @RequestBody JwtRequest authRequest
    ){
//        System.out.println("Method is work");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(
                    new ErrorDto(HttpStatus.UNAUTHORIZED, "Uncorrected username or password"),HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.createToken(userDetails);

        if(token != null && jwtTokenUtils.validateToken(token, userDetails)) {
            return ResponseEntity.ok(new JwtResponse(token));
        }


        return ResponseEntity.ok(new JwtResponse(token));
    }
}
