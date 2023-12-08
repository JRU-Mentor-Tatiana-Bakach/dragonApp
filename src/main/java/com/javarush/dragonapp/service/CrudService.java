package com.javarush.dragonapp.service;

import com.javarush.dragonapp.model.BaseEntity;
import org.springframework.data.domain.Page;

public interface CrudService <E extends BaseEntity>{

    E getById(Long id);

    E save(E e);

    void delete(Long id);

    E update(Long id, E e);

    Page<E> findAll(Integer pageNumber, String sortField, String sortDirection);

}
