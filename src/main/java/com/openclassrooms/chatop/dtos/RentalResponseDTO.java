package com.openclassrooms.chatop.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalResponseDTO {
    private String message;

    public RentalResponseDTO(String message) {
        this.message = message;
    }
}
