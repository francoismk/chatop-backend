package com.openclassrooms.chatop.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRentalDTO {

        private String name;
        private Integer surface;
        private Integer price;
        private String picture;
        private String description;
}
