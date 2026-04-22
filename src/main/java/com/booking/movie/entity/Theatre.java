package com.booking.movie.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "theatre")
@Data
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String address;
}
