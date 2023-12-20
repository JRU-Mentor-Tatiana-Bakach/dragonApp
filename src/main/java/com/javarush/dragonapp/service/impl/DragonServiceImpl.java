package com.javarush.dragonapp.service.impl;

import com.javarush.dragonapp.dto.DragonDTO;
import com.javarush.dragonapp.mapper.DragonMapper;
import com.javarush.dragonapp.model.Dragon;
import com.javarush.dragonapp.model.enums.Color;
import com.javarush.dragonapp.model.enums.Element;
import com.javarush.dragonapp.repository.DragonRepository;
import com.javarush.dragonapp.service.DragonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DragonServiceImpl extends CrudServiceImpl<Dragon, DragonDTO, DragonMapper,
        DragonRepository> implements DragonService {


    protected DragonServiceImpl(DragonRepository repository, DragonMapper mapper) {
        super(repository, mapper, Dragon.class);
    }

    @Override
    public Page<DragonDTO> findDragonByName(String name, Integer pageNumber, String sortField, String sortDirection){
        Pageable pageable = findAllWithPagination(pageNumber, sortField, sortDirection);
        return repository.findDragonByName(name, pageable).map(mapper::toDto);
    }

    @Override
    public Page<DragonDTO> findDragonByColor(Color color, Integer pageNumber, String sortField, String sortDirection){
        Pageable pageable = findAllWithPagination(pageNumber, sortField, sortDirection);
        return repository.findDragonByColor(color, pageable).map(mapper::toDto);
    }

    @Override
    public Integer countDragonByElement(Element element) {
        return repository.countDragonByElement(element);
    }
}
