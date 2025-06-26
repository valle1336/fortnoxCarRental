package com.example.rental.controllers;

import com.example.rental.entities.CarEntity;
import com.example.rental.repositories.CarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarRepository carRepository;

    @Test
    public void testToReturnListOfAllCars() throws Exception {

        List<CarEntity> mockCars = List.of(
                new CarEntity(1, "Volvo S60", 1500),
                new CarEntity(2, "Volkswagen Golf", 1333)
        );
        when(carRepository.findAll()).thenReturn(mockCars);


        mockMvc.perform(get("/cars/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Volvo S60"))
                .andExpect(jsonPath("$[1].name").value("Volkswagen Golf"))
                .andExpect(jsonPath("$[0].pricePerDay").value("1500"))
                .andExpect(jsonPath("$[1].pricePerDay").value("1333"));;

    }
}
