package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dtos.JWTResponseDTO;
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
    public ResponseEntity<JWTResponseDTO>createUser(@Valid @RequestBody DBUserDTO dbUserDTO) {

        JWTResponseDTO token = authService.registerAndGenerateToken(dbUserDTO);

        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JWTResponseDTO> getToken(@RequestBody LoginUserDTO request) {

        JWTResponseDTO token = authService.AuthenticateAndGenerateToken(request);

            return new ResponseEntity<>(token, HttpStatus.OK);
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
