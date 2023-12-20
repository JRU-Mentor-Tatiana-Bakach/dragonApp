package com.javarush.dragonapp.repository;

import com.javarush.dragonapp.BaseTest;
import com.javarush.dragonapp.model.Dragon;
import com.javarush.dragonapp.model.enums.Color;
import com.javarush.dragonapp.model.enums.Element;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class DragonRepositoryTest extends BaseTest {

    private final DragonRepository dragonRepository;

    private final EntityManager entityManager;



    @Test
    void testConnectionDatabase(){
        Assertions.assertNotNull(dragonRepository);
    }

    @Test
    void getById(){
        Dragon dragon = entityManager.find(Dragon.class, 1);
        Assertions.assertNotNull(dragon);
    }

    @Test
    void create(){
        Dragon dragon = Dragon.builder()
                .name("Lava")
                .age(200)
                .color(Color.GREEN)
                .element(Element.EARTH)
                .build();
        entityManager.persist(dragon);
        Assertions.assertNotNull(dragon.getId());

    }
}
