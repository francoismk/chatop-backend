package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.models.DBRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBRentalRepository extends JpaRepository<DBRental, Integer> {
}
