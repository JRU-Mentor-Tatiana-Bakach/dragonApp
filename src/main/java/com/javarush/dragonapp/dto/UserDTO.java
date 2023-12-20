package com.javarush.dragonapp.dto;

import com.javarush.dragonapp.model.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotNull
    private Role role;

    private Long userInfoId;
}
