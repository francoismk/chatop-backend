package com.openclassrooms.chatop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DBMessageDTO {

    @NotBlank(message = "message is required")
    private String message;

    @NotNull(message = "user_id is required")
    private Integer user_id;

    @NotBlank(message = "rental_id is required")
    private Integer rental_id;
}
