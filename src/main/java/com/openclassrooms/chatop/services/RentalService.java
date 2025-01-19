package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dtos.DBRentalDTO;
import com.openclassrooms.chatop.dtos.GetRentalDTO;
import com.openclassrooms.chatop.dtos.UpdateRentalDTO;
import com.openclassrooms.chatop.models.DBRental;
import com.openclassrooms.chatop.models.DBUser;
import com.openclassrooms.chatop.repositories.DBRentalRepository;
import com.openclassrooms.chatop.repositories.DBUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        rental.setOwner(user);
        rentalRepository.save(rental);
    }

    public List<GetRentalDTO> getRentals () {
        List<DBRental> rentals = rentalRepository.findAll();

        return rentals.stream()
                .map(rental -> {
                GetRentalDTO rentalDTO = modelMapper.map(rental, GetRentalDTO.class);
                rentalDTO.setUser_id(rental.getOwner().getId());
                return rentalDTO;
            }).toList();
    }

    public GetRentalDTO getRentalById (Integer id) {
        DBRental rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));

        log.info("Rental found: " + rental);
        GetRentalDTO rentalDTO = modelMapper.map(rental, GetRentalDTO.class);

        rentalDTO.setUser_id(rental.getOwner().getId());

        return rentalDTO;
    }

    public DBRental updateRentalById(Integer id, UpdateRentalDTO updateRentalDto) {
        DBRental rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));

        if (updateRentalDto.getName() != null) {
            rental.setName(updateRentalDto.getName());
        }

        if (updateRentalDto.getSurface() != null) {
            rental.setSurface(updateRentalDto.getSurface());
        }

        if (updateRentalDto.getPrice() != null) {
            rental.setPrice(updateRentalDto.getPrice());
        }

        if (updateRentalDto.getPicture() != null) {
            rental.setPicture(updateRentalDto.getPicture());
        }

        if (updateRentalDto.getDescription() != null) {
            rental.setDescription(updateRentalDto.getDescription());
        }

        return rentalRepository.save(rental);
    }
}
