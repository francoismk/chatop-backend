package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.models.DBRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBRentalRepository extends JpaRepository<DBRental, Integer> {
}
