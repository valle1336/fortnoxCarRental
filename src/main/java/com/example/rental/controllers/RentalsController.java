package com.example.rental.controllers;

import com.example.rental.entities.RentalsEntity;
import com.example.rental.repositories.RentalsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalsController {
    private final RentalsRepository rentalsRepository;

    public RentalsController(RentalsRepository rentalsRepository) {
        this.rentalsRepository = rentalsRepository;
    }

    @GetMapping("/getAll")
    public List<RentalsEntity> getAllRentals() {
        return rentalsRepository.findAll();
    }

    @PostMapping("/create")
    public void addRental(@RequestBody RentalsEntity rental) {
        rentalsRepository.addRental(rental);
    }

}