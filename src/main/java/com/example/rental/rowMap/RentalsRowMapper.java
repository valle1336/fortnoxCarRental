package com.example.rental.rowMap;

import com.example.rental.entities.RentalsEntity;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RentalsRowMapper implements RowMapper<RentalsEntity> {
    @Override
    public RentalsEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RentalsEntity(
                rs.getInt("id"),
                rs.getInt("car_id"),
                rs.getString("driver_name"),
                rs.getInt("driver_age"),
                rs.getObject("start_date", LocalDate.class),
                rs.getObject("end_date", LocalDate.class),
                rs.getInt("revenue")
        );
    }
}
