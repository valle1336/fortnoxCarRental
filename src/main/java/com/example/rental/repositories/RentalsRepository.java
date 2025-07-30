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

    //Välj alla kolumner från Rentals table
    public List<RentalsEntity> findAll() {
        String sql = "SELECT * FROM rentals";
        return jdbcTemplate.query(sql, new RentalsRowMapper());
    }

    //Lägg till ny row i rentals med dynamiska värden från användaren (frontend)
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

    //Returnerar true om bilen är tillgänglig under det angivna datumintervallet
    public boolean isCarAvailable(int carId, LocalDate startDate, LocalDate endDate) {
        //SELECT COUNT(*) ger oss numeriskt värde på hur många objekt rentals table innehåller
        String sql = "SELECT COUNT(*) FROM rentals " +
                //Sedan letar vi efter alla bokningar numeriskt på ett specifikt car_id
                //Sedan kollar vi datumen användaren försöker boka emot bilen som försöker bokas
                "WHERE car_id = ? AND NOT (end_date < ? OR start_date > ?)";
        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                carId,
                startDate,
                endDate
        );
        //Om inget resultat hittas (count = 0), är bilen ledig → return true
        //Om count > 0, finns en överlappning → return false
        return count == null || count == 0;
    }


    //Väldigt lik tidigare exempel
    public LocalDate nextAvailableDate(int carId, LocalDate startDate, LocalDate endDate) {
        //Skillnaden här är SELECT MAX vi hämtar senaste end_date på ett objekt (Kan finnas flera bokningar vi vill ha senaste end_date på objektet)
        String sql = "SELECT MAX(end_date) FROM rentals WHERE car_id = ? AND NOT (end_date < ? OR start_date > ?)";
        LocalDate latestEnd = jdbcTemplate.queryForObject(
                sql, LocalDate.class, carId, startDate, endDate
        );

        //Om det finns en krockande bokning returnerar vi end_date + 1 för att visa tidigaste nästa boknings start
        //Annars är bilen ledig direkt → returnera startDate
        return latestEnd != null ? latestEnd.plusDays(1) : startDate;
    }

}