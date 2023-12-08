package com.javarush.dragonapp.service;

import com.javarush.dragonapp.model.Dragon;
import com.javarush.dragonapp.model.enums.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DragonService extends CrudService<Dragon>{

    Page<Dragon> findDragonByName(String name, Integer pageNumber,
                                  String sortField, String sortDirection);

    Page<Dragon> findDragonByColor(Color color, Integer pageNumber,
                                   String sortField, String sortDirection);
}
