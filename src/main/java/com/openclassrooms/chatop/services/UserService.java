package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dtos.DBUserDTO;
import com.openclassrooms.chatop.dtos.GetUserDTO;
import com.openclassrooms.chatop.dtos.UpdateRentalDTO;
import com.openclassrooms.chatop.errors.exceptions.ResourceNotFoundException;
import com.openclassrooms.chatop.models.DBRental;
import com.openclassrooms.chatop.models.DBUser;
import com.openclassrooms.chatop.repositories.DBRentalRepository;
import com.openclassrooms.chatop.repositories.DBUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    public void saveUser(DBUserDTO userDTO) {
        DBUser user = modelMapper.map(userDTO, DBUser.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public GetUserDTO getUserInfo() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = jwt.getSubject();

        DBUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        return modelMapper.map(user, GetUserDTO.class);
    }

    public boolean userExists(String username) {

        DBUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User with username" + username + "not found");
        }
        return true;
    }

    public GetUserDTO getUserById(Integer id) {
        DBUser user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + "not found"));
        return modelMapper.map(user, GetUserDTO.class);
    }



}
