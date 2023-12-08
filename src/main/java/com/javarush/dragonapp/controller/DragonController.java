package com.javarush.dragonapp.controller;

import com.javarush.dragonapp.dto.DragonDTO;
import com.javarush.dragonapp.exception.DtoValidationException;
import com.javarush.dragonapp.mapper.DragonMapper;
import com.javarush.dragonapp.model.Dragon;
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

    private final DragonServiceImpl dragonService;

    private final DragonMapper dragonMapper;

    @Autowired
    public DragonController(DragonServiceImpl dragonService, DragonMapper dragonMapper) {
        this.dragonService = dragonService;
        this.dragonMapper = dragonMapper;
    }

    @GetMapping(value = "/find/{id}")
    public DragonDTO findById(@PathVariable final Long id) {
        final Dragon dragon = dragonService.getById(id);
        return dragonMapper.toDto(dragon);
    }

    @PostMapping("/create")
    public ResponseEntity<DragonDTO> create(@Valid @RequestBody final DragonDTO dragonDTO,
                                            final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        Dragon dragon = dragonService.save(dragonMapper.toEntity(dragonDTO));
        return new ResponseEntity<>(dragonMapper.toDto(dragon), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<DragonDTO> update(@PathVariable final Long id,
                                            @Valid @RequestBody final DragonDTO dragonDTO,
                                            final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        Dragon dragon = dragonService.update(id, dragonMapper.toEntity(dragonDTO));
        return new ResponseEntity<>(dragonMapper.toDto(dragon), HttpStatus.CREATED);
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
        Page<Dragon> page = dragonService.findAll(pageNumber, sortField, sortDir);
        return page.map(dragonMapper::toDto);
    }

    @GetMapping("/find-name/{name}/{page-number}")
    public Page<DragonDTO> findDragonByName(@PathVariable final String name,
                                            @PathVariable(name = "page-number") final Integer pageNumber,
                                            @RequestParam(name = "sort-field", defaultValue = "id") final String sortField,
                                            @RequestParam(name = "sort-dir", defaultValue = "asc") final String sortDir){
        Page<Dragon> page = dragonService.findDragonByName(name, pageNumber, sortField, sortDir);
        return page.map(dragonMapper::toDto);

    }

}
