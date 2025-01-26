package com.openclassrooms.chatop.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DBRentalDTO {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "surface is required")
    private Integer surface;

    @NotBlank(message = "price is required")
    private Integer price;

    @NotBlank(message = "picture is required")
    private MultipartFile picture;

    @NotBlank(message = "description is required")
    private String description;

}
