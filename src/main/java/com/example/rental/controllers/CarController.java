package com.example.rental.controllers;

import com.example.rental.entities.CarEntity;
import com.example.rental.repositories.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/cars")
public class CarController {
    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/getAll")
    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }
}
