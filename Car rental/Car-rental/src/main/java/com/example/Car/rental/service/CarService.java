package com.example.Car.rental.service;



import java.util.List;

import com.example.Car.rental.dto.CarDTO;

public interface CarService {
    List<CarDTO> getAllCars();
    CarDTO getCarById(Long id);
    CarDTO addCar(CarDTO carDTO);
    CarDTO updateCar(Long id, CarDTO carDTO);
    void deleteCar(Long id);
    
    List<CarDTO> getCarsByBrand(String brand);

    List<CarDTO> getAvailableCars();

    List<CarDTO> getCarsByPriceRange(double minPrice, double maxPrice);

    int countCarsByBrand(String brand);

    CarDTO getMostExpensiveCar();

    
    
    
}
