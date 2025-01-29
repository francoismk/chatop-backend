package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.models.DBMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBMessageRepository extends JpaRepository<DBMessage, Integer> {
    public DBMessage findByMessage(String message);
}
