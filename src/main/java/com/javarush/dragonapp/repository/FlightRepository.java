package com.javarush.dragonapp.repository;

import com.javarush.dragonapp.model.Dragon;
import com.javarush.dragonapp.model.Flight;
import com.javarush.dragonapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends BaseRepository<Flight>{

    Page<Flight> findFlightByDateOfFlightBetween(LocalDate startDate,
                                                 LocalDate endDate, Pageable pageable);
    Integer countFlightByDragon(Dragon dragon);

    List<Flight> findFlightByDragonAndUser(Dragon dragon, User user);

}
