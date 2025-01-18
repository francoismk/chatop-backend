package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dtos.DBRentalDTO;
import com.openclassrooms.chatop.dtos.GetRentalDTO;
import com.openclassrooms.chatop.services.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping()
    public ResponseEntity<String> createRental(@RequestBody DBRentalDTO rentalDTO) {
        rentalService.saveRental(rentalDTO);
        return ResponseEntity.ok("Rental created !");
    }

    @GetMapping()
    public ResponseEntity<GetRentalDTO> getRentals() {
        GetRentalDTO rentals = rentalService.getRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRentalDTO> getRentalById(@PathVariable Integer id) {
        GetRentalDTO rental = rentalService.getRentalById(id);
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }
}
