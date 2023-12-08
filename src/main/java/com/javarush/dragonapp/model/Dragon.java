package com.javarush.dragonapp.model;

import com.javarush.dragonapp.model.enums.Color;
import com.javarush.dragonapp.model.enums.Element;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dragon")
public class Dragon extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.STRING)
    private Element element;
}
