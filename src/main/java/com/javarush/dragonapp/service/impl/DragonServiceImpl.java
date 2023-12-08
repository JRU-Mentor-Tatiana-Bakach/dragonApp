package com.javarush.dragonapp.service.impl;

import com.javarush.dragonapp.model.Dragon;
import com.javarush.dragonapp.model.enums.Color;
import com.javarush.dragonapp.repository.DragonRepository;
import com.javarush.dragonapp.service.DragonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DragonServiceImpl extends CrudServiceImpl<Dragon, DragonRepository> implements DragonService {


    protected DragonServiceImpl(DragonRepository repository) {
        super(repository, Dragon.class);
    }

    @Override
    public Page<Dragon> findDragonByName(String name, Integer pageNumber, String sortField, String sortDirection){
        Pageable pageable = findAllWithPagination(pageNumber, sortField, sortDirection);
        return repository.findDragonByName(name, pageable);
    }

    @Override
    public Page<Dragon> findDragonByColor(Color color, Integer pageNumber, String sortField, String sortDirection){
        Pageable pageable = findAllWithPagination(pageNumber, sortField, sortDirection);
        return repository.findDragonByColor(color, pageable);
    }
}
