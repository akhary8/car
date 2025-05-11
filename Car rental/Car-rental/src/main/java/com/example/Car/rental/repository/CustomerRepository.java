package com.example.Car.rental.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Car.rental.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
