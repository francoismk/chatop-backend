package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dtos.DBUserDTO;
import com.openclassrooms.chatop.dtos.GetUserDTO;
import com.openclassrooms.chatop.errors.exceptions.UserAlreadyExistsException;
import com.openclassrooms.chatop.errors.exceptions.UserNotFoundException;
import com.openclassrooms.chatop.models.DBUser;
import com.openclassrooms.chatop.repositories.DBUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final DBUserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(PasswordEncoder passwordEncoder, DBUserRepository userRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void createUser(DBUserDTO userDTO) {

        DBUser userExists = userRepository.findByEmail(userDTO.getEmail());
        if (userExists != null) {
            throw new UserAlreadyExistsException("User with email" + userDTO.getEmail() + "already exists");
        }
        DBUser user = modelMapper.map(userDTO, DBUser.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public GetUserDTO getUserInfo() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = jwt.getSubject();

        DBUser user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        return modelMapper.map(user, GetUserDTO.class);
    }

    public boolean userExists(String userMail) {

        DBUser email = userRepository.findByEmail(userMail);
        if (email == null) {
            throw new UserNotFoundException("User with email" + userMail + "not found");
        }
        return true;
    }

    public GetUserDTO getUserById(Integer id) {
        DBUser user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + "not found"));
        return modelMapper.map(user, GetUserDTO.class);
    }



}
