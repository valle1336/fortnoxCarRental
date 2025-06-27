package com.example.rental.controllers;

import com.example.rental.entities.RentalsEntity;
import com.example.rental.exceptions.RentalValidationException;
import com.example.rental.repositories.RentalsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<?> addRental(@RequestBody RentalsEntity rental) {
        if (rental.getStartDate().isBefore(LocalDate.now())) {
            throw new RentalValidationException("Start date can't be in the past.");
        }
        if (!rental.getEndDate().isAfter(rental.getStartDate())) {
            throw new RentalValidationException("End date must be after start date.");
        }
        if (rental.getDriverName() == null || rental.getDriverName().isBlank() || rental.getDriverName().matches(".*\\d.*")) {
            throw new RentalValidationException("Driver name can't be empty or contain numbers.");
        }
        if (rental.getDriverAge() < 18) {
            throw new RentalValidationException("Driver must be 18 years or older.");
        }

        rentalsRepository.addRental(rental);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental added!");
        return ResponseEntity.ok(response);
    }

}