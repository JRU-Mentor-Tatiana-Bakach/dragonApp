package com.javarush.dragonapp.model;

import com.javarush.dragonapp.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column
    private LocalDate dateOfBirth;

    @OneToOne(mappedBy = "userInfo",
            cascade = CascadeType.ALL,
            //orphanRemoval = true,
            fetch = FetchType.LAZY)
    private User user;

}
