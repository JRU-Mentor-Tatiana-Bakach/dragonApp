package com.javarush.dragonapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight extends BaseEntity{

    @Column(nullable = false)
    private LocalDate dateOfFlight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dragon_id")
    private Dragon dragon;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private Integer speed;

    @ManyToMany(mappedBy = "flights")
    private Set<Payment> payments;

}
