package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dtos.DBMessageDTO;
import com.openclassrooms.chatop.dtos.GetMessageDTO;
import com.openclassrooms.chatop.dtos.GetRentalDTO;
import com.openclassrooms.chatop.dtos.MessageResponseDTO;
import com.openclassrooms.chatop.services.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping()
    public ResponseEntity<MessageResponseDTO> createMessage(@RequestBody DBMessageDTO messageDTO) {
        if (messageDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        messageService.saveMessage(messageDTO);
        return new ResponseEntity(messageService.saveMessage(messageDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<GetMessageDTO>> getMessages() {
        List<GetMessageDTO> messages = messageService.getAllMessages();
        if (messages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
