package com.openclassrooms.chatop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RentalsResponseDTO {

    private List<GetRentalDTO> rentals;
}
