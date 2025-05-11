package com.example.Car.rental.dto;



import lombok.Data;

@Data
public class CarDTO {
    private Long id;
    private String make;
    private String model;
    private String registrationNumber;
    private Boolean available;
    private Double rentalPrice;
}
