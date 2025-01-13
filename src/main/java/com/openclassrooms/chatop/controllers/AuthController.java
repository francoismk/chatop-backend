package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dtos.DBUserDTO;
import com.openclassrooms.chatop.dtos.GetUserDTO;
import com.openclassrooms.chatop.services.JWTService;
import com.openclassrooms.chatop.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final JWTService jwtService;
    private final UserService userService;

    public AuthController (JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>>createUser(@Valid @RequestBody DBUserDTO dbUserDTO) {
        log.info("Received request to create user: {}", dbUserDTO);
        userService.saveUser(dbUserDTO);

        String token = jwtService.generateTokenFromUsername(dbUserDTO.getUsername());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> getToken(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Authentication failed");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = this.jwtService.generateToken(authentication);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/me")
    public ResponseEntity<GetUserDTO> getUser() {
        log.info("Received request to get user authenticated");
        GetUserDTO getUserDTO = userService.getUserInfo();

        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }
}
