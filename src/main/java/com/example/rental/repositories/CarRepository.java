package com.example.rental.repositories;
import com.example.rental.entities.CarEntity;
import com.example.rental.rowMap.CarRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CarRepository {
    private final JdbcTemplate jdbcTemplate;

    public CarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CarEntity> findAll() {
        String sql = "SELECT * FROM cars";
        return jdbcTemplate.query(sql, new CarRowMapper());
    }
}