package com.openclassrooms.chatop.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMessageDTO {
    private String message;
    private Integer user_id;
    private Integer rental_id;
}
