package com.javarush.dragonapp.dto;

import com.javarush.dragonapp.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserInfoDTO extends BaseDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Email
    private String email;

    @NotBlank
    @Size(max = 10)
    private String phoneNumber;

    private LocalDate dateOfBirth;

    private Long userId;
}
