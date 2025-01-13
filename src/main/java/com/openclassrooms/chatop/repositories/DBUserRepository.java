package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.models.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBUserRepository extends JpaRepository<DBUser, Integer> {
    public DBUser findByUsername(String username);
}
