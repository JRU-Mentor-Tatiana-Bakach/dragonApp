package com.javarush.dragonapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment extends BaseEntity{

    @Column(nullable = false)
    private LocalDate dateOfPayment;

    @Column
    private String bankData;

    @ManyToMany
    @JoinTable(name = "payment_flight",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id")
    )
    //@JsonManagedReference
    private Set<Flight> flights;

//    @JsonManagedReference
//    public Set<Flight> getFlights() {
//        return flights;
//    }
}
