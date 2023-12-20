package com.javarush.dragonapp.controller;

import com.javarush.dragonapp.dto.UserInfoDTO;
import com.javarush.dragonapp.exception.DtoValidationException;
import com.javarush.dragonapp.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping(value = "/find/{id}")
    public UserInfoDTO findById(@PathVariable final Long id) {
        return userInfoService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoDTO create(@Valid @RequestBody final UserInfoDTO userInfoDTO,
                            final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        return userInfoService.save(userInfoDTO);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoDTO update(@PathVariable final Long id,
                            @Valid @RequestBody final UserInfoDTO userInfoDTO,
                            final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        return userInfoService.update(id, userInfoDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserInfoDTO> delete(@PathVariable final Long id) {
        userInfoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/page/{page-number}")
    public Page<UserInfoDTO> findAll(@PathVariable(name = "page-number") final Integer pageNumber,
                                   @RequestParam(name = "sort-field", defaultValue = "id") final String sortField,
                                   @RequestParam(name = "sort-dir", defaultValue = "asc") final String sortDir) {
        return userInfoService.findAll(pageNumber, sortField, sortDir);
    }
}
