package com.example.Car.rental.dto;



import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalDTO {
    private Long id;
    private Long carId;
    private Long customerId;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private Double totalCost;
}
