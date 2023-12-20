package com.javarush.dragonapp.service;

import com.javarush.dragonapp.dto.DragonDTO;
import com.javarush.dragonapp.model.Dragon;
import com.javarush.dragonapp.model.enums.Color;
import com.javarush.dragonapp.model.enums.Element;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DragonService extends CrudService<DragonDTO>{

    Page<DragonDTO> findDragonByName(String name, Integer pageNumber,
                                  String sortField, String sortDirection);

    Page<DragonDTO> findDragonByColor(Color color, Integer pageNumber,
                                   String sortField, String sortDirection);


    Integer countDragonByElement(Element element);
}
