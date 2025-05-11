package com.example.Car.rental.controller;






import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Car.rental.dto.CarDTO;
import com.example.Car.rental.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        CarDTO car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<String> addCar(@RequestBody CarDTO carDTO) {
        carService.addCar(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Car added successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        carService.updateCar(id, carDTO);
        return ResponseEntity.ok("Car updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok("Car deleted successfully.");
    }
}
