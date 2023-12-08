package com.javarush.dragonapp.service.impl;

import com.javarush.dragonapp.model.Flight;
import com.javarush.dragonapp.repository.FlightRepository;
import com.javarush.dragonapp.service.FlightService;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl extends CrudServiceImpl<Flight, FlightRepository> implements FlightService {

    protected FlightServiceImpl(FlightRepository repository) {
        super(repository, Flight.class);
    }
}
