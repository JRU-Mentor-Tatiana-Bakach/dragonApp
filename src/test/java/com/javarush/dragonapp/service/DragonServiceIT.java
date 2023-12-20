package com.javarush.dragonapp.service;

import com.javarush.dragonapp.BaseTest;
import com.javarush.dragonapp.dto.DragonDTO;
import com.javarush.dragonapp.model.enums.Color;
import com.javarush.dragonapp.model.enums.Element;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class DragonServiceIT extends BaseTest {

    private final DragonService dragonService;

    @Test
    void getById(){
        DragonDTO actualResult = dragonService.getById(1L);
        DragonDTO expectedResult = new DragonDTO("Syrax", 120, Color.RED, Element.FIRE);
        Assertions.assertEquals(expectedResult, actualResult);

    }
}
