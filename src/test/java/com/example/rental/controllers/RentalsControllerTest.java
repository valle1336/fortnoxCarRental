package com.example.rental.controllers;

import com.example.rental.entities.RentalsEntity;
import com.example.rental.repositories.RentalsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RentalsController.class)
public class RentalsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalsRepository rentalsRepository;

    @Test
    public void testToReturnListOfAllRentals() throws Exception {
        List<RentalsEntity> mockRentals = List.of(
                new RentalsEntity(1, 1, "Anna", 25, LocalDate.now(), LocalDate.now().plusDays(1), 1500)
        );
        when(rentalsRepository.findAll()).thenReturn(mockRentals);

        mockMvc.perform(get("/rentals/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].driverName").value("Anna"));
    }

    @Test
    public void testAddRentalShouldReturnBadRequestForPastStartDate() throws Exception {
        RentalsEntity rental = new RentalsEntity(
                0, 1, "Bosse", 30, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), 3000
        );

        String json = """
                {
                    "carId": 1,
                    "driverName": "Bosse",
                    "driverAge": 30,
                    "startDate": "%s",
                    "endDate": "%s",
                    "revenue": 3000
                }
                """.formatted(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));

        mockMvc.perform(post("/rentals/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddRental_ReturnsBadRequestForInvalidAge() throws Exception {
        String json = """
                {
                    "carId": 1,
                    "driverName": "Bosse",
                    "driverAge": 17,
                    "startDate": "%s",
                    "endDate": "%s",
                    "revenue": 3000
                }
                """.formatted(LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));

        mockMvc.perform(post("/rentals/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddRental_ReturnsBadRequestForPastStartDate() throws Exception {
        String json = """
                {
                    "carId": 1,
                    "driverName": "Bosse",
                    "driverAge": 30,
                    "startDate": "%s",
                    "endDate": "%s",
                    "revenue": 3000
                }
                """.formatted(LocalDate.now().minusDays(2), LocalDate.now().plusDays(2));

        mockMvc.perform(post("/rentals/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
