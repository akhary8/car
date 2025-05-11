package com.example.Car.rental.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Car.rental.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}

