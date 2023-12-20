package com.javarush.dragonapp.service;

import com.javarush.dragonapp.dto.FlightDTO;
import com.javarush.dragonapp.model.Dragon;
import com.javarush.dragonapp.model.Flight;
import com.javarush.dragonapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface FlightService extends CrudService<FlightDTO>{

    Page<FlightDTO> findFlightByDateOfFlightBetween(LocalDate startDate, LocalDate endDate,
                                                 Integer pageNumber,
                                                 String sortField, String sortDirection);
    Integer countFlightByDragon(Dragon dragon);

    List<FlightDTO> findFlightByDragonAndUser(Dragon dragon, User user);
}
