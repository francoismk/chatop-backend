package com.openclassrooms.chatop.errors.exceptions;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
