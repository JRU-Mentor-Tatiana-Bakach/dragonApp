package com.javarush.dragonapp.service.impl;

import com.javarush.dragonapp.dto.FlightDTO;
import com.javarush.dragonapp.mapper.FlightMapper;
import com.javarush.dragonapp.model.Dragon;
import com.javarush.dragonapp.model.Flight;
import com.javarush.dragonapp.model.User;
import com.javarush.dragonapp.repository.FlightRepository;
import com.javarush.dragonapp.service.FlightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightServiceImpl extends CrudServiceImpl<Flight, FlightDTO, FlightMapper,
        FlightRepository> implements FlightService {

    protected FlightServiceImpl(FlightRepository repository, FlightMapper mapper) {
        super(repository, mapper, Flight.class);
    }

    @Override
    public Page<FlightDTO> findFlightByDateOfFlightBetween(LocalDate startDate, LocalDate endDate,
                                                        Integer pageNumber, String sortField, String sortDirection) {
        Pageable pageable = findAllWithPagination(pageNumber, sortField, sortDirection);
        return repository.findFlightByDateOfFlightBetween(startDate, endDate, pageable).map(mapper::toDto);
    }

    @Override
    public Integer countFlightByDragon(Dragon dragon) {
        return repository.countFlightByDragon(dragon);
    }

    @Override
    public List<FlightDTO> findFlightByDragonAndUser(Dragon dragon, User user) {
        return repository.findFlightByDragonAndUser(dragon, user)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
