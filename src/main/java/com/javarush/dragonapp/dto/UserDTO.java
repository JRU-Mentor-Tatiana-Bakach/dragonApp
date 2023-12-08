package com.javarush.dragonapp.dto;

import com.javarush.dragonapp.model.UserInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO extends BaseDTO {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    private Long userInfoId;
}
