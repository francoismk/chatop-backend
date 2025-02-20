package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.models.DBMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBMessageRepository extends JpaRepository<DBMessage, Integer> {
}
