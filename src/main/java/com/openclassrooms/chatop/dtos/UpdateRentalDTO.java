package com.openclassrooms.chatop.dtos;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateRentalDTO {

        @Size(max = 255, message = "Value too long for name")
        private String name;

        private Integer surface;

        private Integer price;

        private MultipartFile picture;

        @Size(max = 255, message = "Value too long for description")
        private String description;
}
