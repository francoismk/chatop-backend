package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dtos.DBMessageDTO;
import com.openclassrooms.chatop.dtos.GetMessageDTO;
import com.openclassrooms.chatop.dtos.MessageResponseDTO;
import com.openclassrooms.chatop.errors.exceptions.ResourceNotFoundException;
import com.openclassrooms.chatop.errors.exceptions.UserNotFoundException;
import com.openclassrooms.chatop.models.DBMessage;
import com.openclassrooms.chatop.models.DBRental;
import com.openclassrooms.chatop.models.DBUser;
import com.openclassrooms.chatop.repositories.DBMessageRepository;
import com.openclassrooms.chatop.repositories.DBRentalRepository;
import com.openclassrooms.chatop.repositories.DBUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final DBMessageRepository dbMessageRepository;
    private final DBUserRepository dbUserRepository;
    private final DBRentalRepository dbRentalRepository;
    private final ModelMapper modelMapper;

    public MessageService(DBMessageRepository dbMessageRepository, DBUserRepository dbUserRepository, DBRentalRepository dbRentalRepository, ModelMapper modelMapper) {
        this.dbMessageRepository = dbMessageRepository;
        this.dbUserRepository = dbUserRepository;
        this.dbRentalRepository = dbRentalRepository;
        this.modelMapper = modelMapper;
    }

    public MessageResponseDTO saveMessage(DBMessageDTO messageDTO) {

        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = jwt.getSubject();

        DBUser userMail = dbUserRepository.findByEmail(email);
        if (userMail == null) {
            throw new UserNotFoundException("User with email" + email + "not found");
        }

        DBRental rental = dbRentalRepository.findById(messageDTO.getRental_id()).orElse(null);
        if (rental == null) {
            throw new ResourceNotFoundException("Rental not found");
        }

        DBMessage message = modelMapper.map(messageDTO, DBMessage.class);
        message.setUserId(userMail);
        message.setRentalId(rental);
        dbMessageRepository.save(message);

        return new MessageResponseDTO("Message created !");
    }

    public List<GetMessageDTO> getAllMessages() {
        List<DBMessage> messages = dbMessageRepository.findAll();

        return messages.stream()
                .map(message -> {
                    GetMessageDTO messageDTO = modelMapper.map(message, GetMessageDTO.class);
                    messageDTO.setUser_id(message.getUserId().getId());
                    messageDTO.setRental_id(message.getRentalId().getId());
                    return messageDTO;
                }).toList();
    }
}
