package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dtos.*;
import com.openclassrooms.chatop.services.RentalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<RentalResponseDTO> createRental(@Valid @ModelAttribute DBRentalDTO rentalDTO) {
        return ResponseEntity.ok(rentalService.saveRental(rentalDTO));
    }

    @GetMapping()
    public ResponseEntity<ListRentalsResponseDTO> getRentals() {
        List<GetRentalDTO> rentals = rentalService.getRentals();
        return new ResponseEntity<>(new ListRentalsResponseDTO(rentals), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRentalDTO> getRentalById(@PathVariable Integer id) {
        GetRentalDTO rental = rentalService.getRentalById(id);
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<RentalResponseDTO> updateRentalById(@PathVariable Integer id, @Valid @ModelAttribute UpdateRentalDTO updateRentalDTO) {
        return ResponseEntity.ok(rentalService.updateRentalById(id, updateRentalDTO));
    }
}
