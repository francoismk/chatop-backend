package com.openclassrooms.chatop.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponseDTO {

    private String message;

    public MessageResponseDTO(String message) {
        this.message = message;
    }
}

