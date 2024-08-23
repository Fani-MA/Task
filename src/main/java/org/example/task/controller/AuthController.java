package org.example.task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {


    UserServiceImpl userService;
    JwtTokenUtils  jwtTokenUtils;
    AuthenticationManager authenticationManager;

    @Operation(summary = "Auth",description = "Auth users with email and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success auth",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/auth")
    public ResponseEntity<?> createToken(@Valid @RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(
                    new ErrorDto(HttpStatus.UNAUTHORIZED, "Uncorrected username or password"),HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
        String token = jwtTokenUtils.createToken(userDetails);

        if(token != null && jwtTokenUtils.validateToken(token, userDetails)) {
            return ResponseEntity.ok(new JwtResponse(token));
        }

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
