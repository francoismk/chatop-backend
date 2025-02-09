package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dtos.DBUserDTO;
import com.openclassrooms.chatop.dtos.GetUserDTO;
import com.openclassrooms.chatop.dtos.LoginUserDTO;
import com.openclassrooms.chatop.services.AuthService;
import com.openclassrooms.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController (UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>>createUser(@Valid @RequestBody DBUserDTO dbUserDTO) {

        String token = authService.registerAndGenerateToken(dbUserDTO);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> getToken(@RequestBody LoginUserDTO request) {

            String token = authService.AuthenticateAndGenerateToken(request);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return new ResponseEntity<>(response, HttpStatus.OK);
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
