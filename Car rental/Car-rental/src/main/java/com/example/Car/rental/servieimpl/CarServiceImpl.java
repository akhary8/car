package com.example.Car.rental.servieimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Car.rental.dto.CarDTO;
import com.example.Car.rental.entity.Car;
import com.example.Car.rental.exception.CarNotFoundException;
import com.example.Car.rental.exception.DuplicateResourceException;
import com.example.Car.rental.exception.InvalidDataException;
import com.example.Car.rental.exception.ResourceNotFoundException;
import com.example.Car.rental.repository.CarRepository;
import com.example.Car.rental.service.CarService;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with ID " + id + " not found."));
        return mapToDTO(car);
    }

    @Override
    public CarDTO addCar(CarDTO carDTO) {
        if (carDTO.getRegistrationNumber() == null || carDTO.getRegistrationNumber().isEmpty()) {
            throw new InvalidDataException("Registration number cannot be null or empty.");
        }

        boolean exists = carRepository.findAll().stream()
                .anyMatch(car -> car.getRegistrationNumber().equals(carDTO.getRegistrationNumber()));
        if (exists) {
            throw new DuplicateResourceException("Car with registration number " + carDTO.getRegistrationNumber() + " already exists.");
        }

        Car car = mapToEntity(carDTO);
        car = carRepository.save(car);
        return mapToDTO(car);
    }

    @Override
    public CarDTO updateCar(Long id, CarDTO carDTO) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with ID " + id + " not found."));
        car.setMake(carDTO.getMake());
        car.setModel(carDTO.getModel());
        car.setRegistrationNumber(carDTO.getRegistrationNumber());
        car.setAvailable(carDTO.getAvailable());
        car.setRentalPrice(carDTO.getRentalPrice());
        car = carRepository.save(car);
        return mapToDTO(car);
    }

    @Override
    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new ResourceNotFoundException("Car with ID " + id + " not found.");
        }
        carRepository.deleteById(id);
    }

    // Native Queries
    @Override
    public List<CarDTO> getCarsByBrand(String brand) {
        List<Car> cars = carRepository.findCarsByBrand(brand);  // Native query in repository

        if (cars.isEmpty()) {
            throw new CarNotFoundException("No cars found for brand: " + brand);
        }

        return cars.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> getAvailableCars() {
        List<Car> cars = carRepository.findAvailableCars();  // Native query in repository
        return cars.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> getCarsByPriceRange(double minPrice, double maxPrice) {
        List<Car> cars = carRepository.findCarsByPriceRange(minPrice, maxPrice);  // Native query in repository
        return cars.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public int countCarsByBrand(String brand) {
        return carRepository.countCarsByBrand(brand);  // Native query in repository
    }

    @Override
    public CarDTO getMostExpensiveCar() {
        Car car = carRepository.findMostExpensiveCar();  // Native query in repository
        return mapToDTO(car);
    }

    // Map entity to DTO
    private CarDTO mapToDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setMake(car.getMake());
        carDTO.setModel(car.getModel());
        carDTO.setRegistrationNumber(car.getRegistrationNumber());
        carDTO.setAvailable(car.getAvailable());
        carDTO.setRentalPrice(car.getRentalPrice());
        return carDTO;
    }

    // Map DTO to entity
    private Car mapToEntity(CarDTO carDTO) {
        Car car = new Car();
        car.setMake(carDTO.getMake());
        car.setModel(carDTO.getModel());
        car.setRegistrationNumber(carDTO.getRegistrationNumber());
        car.setAvailable(carDTO.getAvailable());
        car.setRentalPrice(carDTO.getRentalPrice());
        return car;
    }
}




