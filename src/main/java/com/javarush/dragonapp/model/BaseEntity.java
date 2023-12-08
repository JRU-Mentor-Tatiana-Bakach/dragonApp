package com.javarush.dragonapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
//@AllArgsConstructor можно использовать аннотации или просто написать конструктор
// можно делать как interface так и абстрактный класс
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private Long id;

//    public BaseEntity(Integer id) {
//        this.id = id;
//    }
}
