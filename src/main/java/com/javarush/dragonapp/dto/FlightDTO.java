package com.javarush.dragonapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
public class FlightDTO extends BaseDTO {

    @NotNull
    private LocalDate dateOfFlight;

    @NotNull
    private Long userId;

    @NotNull
    private Long dragonId;

    @Positive
    @NotNull
    private BigDecimal price;

    private Integer speed;

//    private Set<PaymentDTO> payments;
}
