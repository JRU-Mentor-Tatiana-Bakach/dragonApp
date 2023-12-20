package com.javarush.dragonapp.service;

import com.javarush.dragonapp.dto.BaseDTO;
import com.javarush.dragonapp.model.BaseEntity;
import org.springframework.data.domain.Page;

public interface CrudService <D extends BaseDTO>{

    D getById(Long id);

    D save(D d);

    void delete(Long id);

    D update(Long id, D e);

    Page<D> findAll(Integer pageNumber, String sortField, String sortDirection);

}
