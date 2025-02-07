package com.openclassrooms.chatop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DBRentalDTO {

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "surface is required")
    private Integer surface;

    @NotNull(message = "price is required")
    private Integer price;

    @NotNull(message = "picture is required")
    private MultipartFile picture;

    @NotNull(message = "description is required")
    private String description;

}
