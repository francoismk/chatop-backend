package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dtos.DBRentalDTO;
import com.openclassrooms.chatop.dtos.GetRentalDTO;
import com.openclassrooms.chatop.dtos.UpdateRentalDTO;
import com.openclassrooms.chatop.services.RentalService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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
    public ResponseEntity<String> createRental(@Valid @ModelAttribute DBRentalDTO rentalDTO) {
        rentalService.saveRental(rentalDTO);
        return ResponseEntity.ok("Rental created !");
    }

    @GetMapping()
    public ResponseEntity<List<GetRentalDTO>> getRentals() {
        List<GetRentalDTO> rentals = rentalService.getRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRentalDTO> getRentalById(@PathVariable Integer id) {
        GetRentalDTO rental = rentalService.getRentalById(id);
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<String> updateRentalById(@PathVariable Integer id, @Valid @ModelAttribute UpdateRentalDTO updateRentalDTO) {
        rentalService.updateRentalById(id, updateRentalDTO);
        return ResponseEntity.ok("Rental updated !");
    }
}
