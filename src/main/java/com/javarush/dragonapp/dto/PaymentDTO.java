package com.javarush.dragonapp.dto;

import com.javarush.dragonapp.model.Flight;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
public class PaymentDTO extends BaseDTO {

    @NotNull
    private LocalDate dateOfPayment;

    private String bankData;

    @NotNull
    private Set<Flight> flights;
}
