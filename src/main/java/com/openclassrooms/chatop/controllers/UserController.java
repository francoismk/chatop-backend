package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dtos.GetUserDTO;
import com.openclassrooms.chatop.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserDTO> getUserById(@PathVariable Integer id) {
        GetUserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
