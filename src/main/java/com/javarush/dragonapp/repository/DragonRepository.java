package com.javarush.dragonapp.repository;

import com.javarush.dragonapp.model.Dragon;
import com.javarush.dragonapp.model.enums.Color;
import com.javarush.dragonapp.model.enums.Element;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface DragonRepository extends BaseRepository<Dragon>{

    Page<Dragon> findDragonByName(String name, Pageable pageable);

    Page<Dragon> findDragonByColor(Color color, Pageable pageable);

    Page<Dragon> findDragonByColorIsNot(Color color, Pageable pageable);

    Page<Dragon> findDragonByColorIsNull(Pageable pageable);

    Integer countDragonByElement(Element element);

    Page<Dragon> findDragonByAgeGreaterThan(Integer age, Pageable pageable);

    Page<Dragon> findDragonByElement(Element element, Pageable pageable);

}
