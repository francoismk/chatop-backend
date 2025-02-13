package com.openclassrooms.chatop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DBRentalDTO {

    @NotNull(message = "name is required")
    @Size(max = 255, message = "Value too long for name")
    private String name;

    @NotNull(message = "surface is required")
    private Integer surface;

    @NotNull(message = "price is required")
    private Integer price;

    @NotNull(message = "picture is required")
    private MultipartFile picture;

    @NotNull(message = "description is required")
    @Size(max = 255, message = "Value too long for description")
    private String description;

}
