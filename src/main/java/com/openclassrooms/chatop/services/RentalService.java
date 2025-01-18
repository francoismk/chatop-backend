package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dtos.DBRentalDTO;
import com.openclassrooms.chatop.dtos.GetRentalDTO;
import com.openclassrooms.chatop.models.DBRental;
import com.openclassrooms.chatop.models.DBUser;
import com.openclassrooms.chatop.repositories.DBRentalRepository;
import com.openclassrooms.chatop.repositories.DBUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class RentalService {

    @Autowired
    private DBRentalRepository rentalRepository;

    @Autowired
    private DBUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void saveRental(DBRentalDTO rentalDTO) {

        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = jwt.getSubject();

        DBUser user = userRepository.findByUsername(username);

        DBRental rental = modelMapper.map(rentalDTO, DBRental.class);
        rental.setOwner_id(user);
        rentalRepository.save(rental);
    }

    public GetRentalDTO getRentals () {
        return modelMapper.map(rentalRepository.findAll(), GetRentalDTO.class);
    }

    public GetRentalDTO getRentalById (Integer id) {
        DBRental rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));

        GetRentalDTO rentalDTO = modelMapper.map(rental, GetRentalDTO.class);

        rentalDTO.setUser_id(rental.getOwner_id().getId());

        return rentalDTO;
    }
}
