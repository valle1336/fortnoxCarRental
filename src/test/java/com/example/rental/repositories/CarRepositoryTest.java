package com.example.rental.repositories;

import com.example.rental.entities.CarEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testFindAll_ReturnsCars() {
        // Act: Hämta alla bilar
        List<CarEntity> cars = carRepository.findAll();

        // Assert: Listan ska inte vara tom (du har ju laddat in bilar från början)
        assertFalse(cars.isEmpty(), "Car list should not be empty");
        // Exempel: kontrollera att Volvo S60 finns
        assertTrue(cars.stream().anyMatch(c -> c.getName().equals("Volvo S60")));
    }
}
