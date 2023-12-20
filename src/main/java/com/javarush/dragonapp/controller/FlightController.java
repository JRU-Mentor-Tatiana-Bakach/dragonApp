package com.javarush.dragonapp.controller;

import com.javarush.dragonapp.dto.DragonDTO;
import com.javarush.dragonapp.dto.FlightDTO;
import com.javarush.dragonapp.exception.DtoValidationException;
import com.javarush.dragonapp.model.Dragon;
import com.javarush.dragonapp.model.User;
import com.javarush.dragonapp.model.enums.Color;
import com.javarush.dragonapp.model.enums.Element;
import com.javarush.dragonapp.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping(value = "/find/{id}")
    public FlightDTO findById(@PathVariable final Long id) {
        return flightService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDTO create(@Valid @RequestBody final FlightDTO flightDTO,
                            final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        return flightService.save(flightDTO);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDTO update(@PathVariable final Long id,
                            @Valid @RequestBody final FlightDTO flightDTO,
                            final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        return flightService.update(id, flightDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FlightDTO> delete(@PathVariable final Long id) {
        flightService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/page/{page-number}")
    public Page<FlightDTO> findAll(@PathVariable(name = "page-number") final Integer pageNumber,
                                   @RequestParam(name = "sort-field", defaultValue = "id") final String sortField,
                                   @RequestParam(name = "sort-dir", defaultValue = "asc") final String sortDir) {
        return flightService.findAll(pageNumber, sortField, sortDir);
    }

    @GetMapping("/find-date-between")
    public Page<FlightDTO> findFlightByDateOfFlightBetween(
            @RequestParam(name = "date-start", defaultValue = "2023-01-01") final LocalDate dateStart,
            @RequestParam(name = "date-end", defaultValue = "2023-12-31") final LocalDate dateEnd,
            @RequestParam(name = "page-number", defaultValue = "1") final Integer pageNumber,
            @RequestParam(name = "sort-field", defaultValue = "id") final String sortField,
            @RequestParam(name = "sort-dir", defaultValue = "asc") final String sortDir) {
        return flightService.findFlightByDateOfFlightBetween(dateStart, dateEnd, pageNumber, sortField, sortDir);
    }

    @GetMapping("/count-dragon/{dragon}")
    public Integer countFlightByDragon(Dragon dragon) {
        return flightService.countFlightByDragon(dragon);
    }

    @GetMapping("/find-dragon-user")
    public List<FlightDTO> findFlightByDragonAndUser(
            @RequestParam(name = "dragon-id", defaultValue = "1") final Dragon dragon,
            @RequestParam(name = "user-id", defaultValue = "1") final User user) {
        return flightService.findFlightByDragonAndUser(dragon, user);
    }
}
