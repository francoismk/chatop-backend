package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dtos.DBUserDTO;
import com.openclassrooms.chatop.dtos.JWTResponseDTO;
import com.openclassrooms.chatop.dtos.LoginUserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthService(JWTService jwtService, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public JWTResponseDTO AuthenticateAndGenerateToken(LoginUserDTO userDTO) throws BadCredentialsException {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));

        return jwtService.generateToken(authentication);
    }

    public JWTResponseDTO registerAndGenerateToken(DBUserDTO userDTO) {
        userService.createUser(userDTO);
        return jwtService.generateTokenFromEmail(userDTO.getEmail());
    }
}
