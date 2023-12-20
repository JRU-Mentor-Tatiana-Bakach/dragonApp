package com.javarush.dragonapp.controller;

import com.javarush.dragonapp.dto.PaymentDTO;
import com.javarush.dragonapp.dto.UserInfoDTO;
import com.javarush.dragonapp.exception.DtoValidationException;
import com.javarush.dragonapp.service.PaymentService;
import com.javarush.dragonapp.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(value = "/find/{id}")
    public PaymentDTO findById(@PathVariable final Long id) {
        return paymentService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDTO create(@Valid @RequestBody final PaymentDTO paymentDTO,
                              final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        return paymentService.save(paymentDTO);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDTO update(@PathVariable final Long id,
                              @Valid @RequestBody final PaymentDTO paymentDTO,
                              final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        return paymentService.update(id, paymentDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PaymentDTO> delete(@PathVariable final Long id) {
        paymentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/page/{page-number}")
    public Page<PaymentDTO> findAll(@PathVariable(name = "page-number") final Integer pageNumber,
                                     @RequestParam(name = "sort-field", defaultValue = "id") final String sortField,
                                     @RequestParam(name = "sort-dir", defaultValue = "asc") final String sortDir) {
        return paymentService.findAll(pageNumber, sortField, sortDir);
    }
}
