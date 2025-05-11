package com.example.Car.rental.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Car.rental.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
	


    // Native query to find cars by brand
    @Query(value = "SELECT * FROM cars WHERE brand = :brand", nativeQuery = true)
    List<Car> findCarsByBrand(@Param("brand") String brand);

    // Native query to find available cars
    @Query(value = "SELECT * FROM cars WHERE is_available = 1", nativeQuery = true)
    List<Car> findAvailableCars();

    // Native query to find cars within a price range
    @Query(value = "SELECT * FROM cars WHERE rental_price_per_day BETWEEN :minPrice AND :maxPrice", nativeQuery = true)
    List<Car> findCarsByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    // Native query to count cars by brand
    @Query(value = "SELECT COUNT(*) FROM cars WHERE brand = :brand", nativeQuery = true)
    int countCarsByBrand(@Param("brand") String brand);

    // Native query to find the most expensive car
    @Query(value = "SELECT * FROM cars ORDER BY rental_price_per_day DESC LIMIT 1", nativeQuery = true)
    Car findMostExpensiveCar();
	
	
}

