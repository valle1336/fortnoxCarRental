package com.example.rental.repositories;

import com.example.rental.entities.RentalsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentalsRepositoryTest {

    @Autowired
    private RentalsRepository rentalsRepository;

    @Test
    public void testAddAndFindAllRentals() {
        RentalsEntity rental = new RentalsEntity(
                0,
                1,
                "TestUser",
                25,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                2500
        );


        rentalsRepository.addRental(rental);
        List<RentalsEntity> allRentals = rentalsRepository.findAll();


        assertTrue(allRentals.stream().anyMatch(r -> r.getDriverName().equals("TestUser")));
    }
}
