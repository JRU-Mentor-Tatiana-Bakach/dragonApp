package com.javarush.dragonapp.dto;

import com.javarush.dragonapp.model.enums.Color;
import com.javarush.dragonapp.model.enums.Element;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DragonDTO extends BaseDTO {

    @NotBlank
    @Size(max = 100)
    private String name;

    @Positive
    private Integer age;

    private Color color;

    private Element element;
}
