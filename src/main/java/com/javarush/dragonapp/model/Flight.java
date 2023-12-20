package com.javarush.dragonapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    //@JsonManagedReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dragon_id")
    //@JsonManagedReference
    private Dragon dragon;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private Integer speed;

    @ManyToMany(mappedBy = "flights")
    //@JsonBackReference
    private Set<Payment> payments;

//    @JsonBackReference
//    public Set<Payment> getPayments() {
//        return payments;
//    }

//    @JsonBackReference
//    public User getUser() {
//        return user;
//    }

//    @JsonBackReference
//    public Dragon getDragon() {
//        return dragon;
//    }
}
