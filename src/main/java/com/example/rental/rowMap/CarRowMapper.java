package com.example.rental.rowMap;
import com.example.rental.entities.CarEntity;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<CarEntity> {
    @Override
    public CarEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CarEntity(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price_per_day")
        );
    }
}