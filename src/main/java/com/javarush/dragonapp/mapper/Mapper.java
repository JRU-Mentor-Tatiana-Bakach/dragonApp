package com.javarush.dragonapp.mapper;

import com.javarush.dragonapp.dto.BaseDTO;
import com.javarush.dragonapp.model.BaseEntity;

public interface Mapper<E extends BaseEntity, D extends BaseDTO> {

    E toEntity(D dto);

    D toDto(E entity);
}