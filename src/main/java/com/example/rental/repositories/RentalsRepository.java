package com.example.rental.repositories;

import com.example.rental.entities.RentalsEntity;
import com.example.rental.rowMap.RentalsRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RentalsRepository {
    private final JdbcTemplate jdbcTemplate;

    public RentalsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RentalsEntity> findAll() {
        String sql = "SELECT * FROM rentals";
        return jdbcTemplate.query(sql, new RentalsRowMapper());
    }

    public void addRental(RentalsEntity rental) {
        String sql = "INSERT INTO rentals (car_id, driver_name, driver_age, start_date, end_date, revenue) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                rental.getCarId(),
                rental.getDriverName(),
                rental.getDriverAge(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getRevenue()
        );
    }
    public boolean isCarAvailable(int carId, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT COUNT(*) FROM rentals " +
                "WHERE car_id = ? AND NOT (end_date < ? OR start_date > ?)";
        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                carId,
                startDate,
                endDate
        );
        return count == null || count == 0;
    }

    public LocalDate nextAvailableDate(int carId, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT MAX(end_date) FROM rentals WHERE car_id = ? AND NOT (end_date < ? OR start_date > ?)";
        LocalDate latestEnd = jdbcTemplate.queryForObject(
                sql, LocalDate.class, carId, startDate, endDate
        );

        return latestEnd != null ? latestEnd.plusDays(1) : startDate;
    }

}