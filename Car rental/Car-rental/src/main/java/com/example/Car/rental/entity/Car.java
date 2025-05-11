package com.example.Car.rental.entity;



import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String model;
    private String registrationNumber;
    private Boolean available;
    private Double rentalPrice;
}

