package com.openclassrooms.chatop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "MESSAGES")
@Getter
@Setter
public class DBMessage {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private DBRental rentalId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private DBUser userId;

    @Column(name = "message")
    private String message;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date created_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updated_at;
}