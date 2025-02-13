package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dtos.DBUserDTO;
import com.openclassrooms.chatop.dtos.GetUserDTO;
import com.openclassrooms.chatop.errors.exceptions.UserAlreadyExistsException;
import com.openclassrooms.chatop.errors.exceptions.UserNotFoundException;
import com.openclassrooms.chatop.models.DBUser;
import com.openclassrooms.chatop.repositories.DBRentalRepository;
import com.openclassrooms.chatop.repositories.DBUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DBUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DBRentalRepository rentalRepository;

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
            throw new UserNotFoundException("User" + user.getName() + "not found");
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
