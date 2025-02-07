package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dtos.DBUserDTO;
import com.openclassrooms.chatop.dtos.GetUserDTO;
import com.openclassrooms.chatop.dtos.LoginUserDTO;
import com.openclassrooms.chatop.services.JWTService;
import com.openclassrooms.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final JWTService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController (JWTService jwtService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>>createUser(@Valid @RequestBody DBUserDTO dbUserDTO) {
        log.info("Received request to create user: {}", dbUserDTO);

        if(userService.userExists(dbUserDTO.getUsername())) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "User already exists");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        userService.saveUser(dbUserDTO);

        String token = jwtService.generateTokenFromUsername(dbUserDTO.getUsername());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> getToken(@RequestBody LoginUserDTO request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            String token = jwtService.generateToken(authentication);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Bad credentials");
            // Return 401 Unauthorized via global exception handler
            // retirer la logique du controller
            // retirer le try/catch
            // mettre en place bad exception handler
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/me")
    @Operation(
            summary = "Get user authenticated",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    public ResponseEntity<GetUserDTO> getUser() {
        log.info("Received request to get user authenticated");
        GetUserDTO getUserDTO = userService.getUserInfo();

        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }
}
