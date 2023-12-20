package com.javarush.dragonapp.controller;

import com.javarush.dragonapp.dto.UserDTO;
import com.javarush.dragonapp.exception.DtoValidationException;
import com.javarush.dragonapp.model.enums.Role;
import com.javarush.dragonapp.service.UserService;
import com.javarush.dragonapp.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/find/{id}")
    public UserDTO findById(@PathVariable final Long id) {
        return userService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody final UserDTO userDTO,
                          final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        return userService.save(userDTO);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO update(@PathVariable final Long id,
                          @Valid @RequestBody final UserDTO userDTO,
                          final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        return userService.update(id, userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable final Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/page/{page-number}")
    public Page<UserDTO> findAll(@PathVariable(name = "page-number") final Integer pageNumber,
                                   @RequestParam(name = "sort-field", defaultValue = "id") final String sortField,
                                   @RequestParam(name = "sort-dir", defaultValue = "desc") final String sortDir) {
        return userService.findAll(pageNumber, sortField, sortDir);
    }

    @GetMapping("/find-name/{name}")
    public Optional<UserDTO> findUserByName(@PathVariable final String name) {
        return userService.findUserByUserName(name);
    }

    @GetMapping("/find-role/{role}")
    public Page<UserDTO> findUserByName(@PathVariable final Role role,
                                            @RequestParam(name = "page-number", defaultValue = "1") final Integer pageNumber,
                                            @RequestParam(name = "sort-field", defaultValue = "id") final String sortField,
                                            @RequestParam(name = "sort-dir", defaultValue = "asc") final String sortDir) {
        return userService.findUserByRole(role,pageNumber, sortField, sortDir);
    }
}
