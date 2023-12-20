package com.javarush.dragonapp.controller;

import com.javarush.dragonapp.dto.DragonDTO;
import com.javarush.dragonapp.exception.DtoValidationException;
import com.javarush.dragonapp.model.enums.Color;
import com.javarush.dragonapp.model.enums.Element;
import com.javarush.dragonapp.service.DragonService;
import com.javarush.dragonapp.service.impl.DragonServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dragons")
public class DragonController {

    private final DragonService dragonService;

    @Autowired
    public DragonController(DragonService dragonService) {
        this.dragonService = dragonService;
    }

    @GetMapping(value = "/find/{id}")
    public DragonDTO findById(@PathVariable final Long id) {
        return dragonService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public DragonDTO create(@Valid @RequestBody final DragonDTO dragonDTO,
                            final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        return dragonService.save(dragonDTO);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public DragonDTO update(@PathVariable final Long id,
                            @Valid @RequestBody final DragonDTO dragonDTO,
                            final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        return dragonService.update(id, dragonDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DragonDTO> delete(@PathVariable final Long id) {
        dragonService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/page/{page-number}")
    public Page<DragonDTO> findAll(@PathVariable(name = "page-number") final Integer pageNumber,
                                   @RequestParam(name = "sort-field", defaultValue = "id") final String sortField,
                                   @RequestParam(name = "sort-dir", defaultValue = "asc") final String sortDir) {
        return dragonService.findAll(pageNumber, sortField, sortDir);
    }

    @GetMapping("/find-name/{name}")
    public Page<DragonDTO> findDragonByName(@PathVariable final String name,
                                            @RequestParam(name = "page-number", defaultValue = "1") final Integer pageNumber,
                                            @RequestParam(name = "sort-field", defaultValue = "id") final String sortField,
                                            @RequestParam(name = "sort-dir", defaultValue = "asc") final String sortDir) {
        return dragonService.findDragonByName(name, pageNumber, sortField, sortDir);
    }

    @GetMapping("/find-color/{color}")
    public Page<DragonDTO> findDragonByColor(@PathVariable final Color color,
                                             @RequestParam(name = "page-number", defaultValue = "1") final Integer pageNumber,
                                             @RequestParam(name = "sort-field", defaultValue = "id") final String sortField,
                                             @RequestParam(name = "sort-dir", defaultValue = "asc") final String sortDir) {
        return dragonService.findDragonByColor(color, pageNumber, sortField, sortDir);
    }

    @GetMapping("/count-element/{element}")
    public Integer countDragonByElement(@PathVariable final Element element) {
        return dragonService.countDragonByElement(element);
    }
}
