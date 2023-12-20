package com.javarush.dragonapp.service.impl;

import com.javarush.dragonapp.dto.BaseDTO;
import com.javarush.dragonapp.exception.EntityNotFoundException;
import com.javarush.dragonapp.exception.SaveEntityException;
import com.javarush.dragonapp.mapper.BaseMapper;
import com.javarush.dragonapp.mapper.Mapper;
import com.javarush.dragonapp.model.BaseEntity;
import com.javarush.dragonapp.repository.BaseRepository;
import com.javarush.dragonapp.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
public abstract class CrudServiceImpl<E extends BaseEntity, D extends BaseDTO,
        M extends Mapper<E, D>, R extends BaseRepository<E>> implements CrudService<D> {

    final R repository;

    final Class<E> genericType;

    final M mapper;

    protected CrudServiceImpl(R repository, M mapper, Class<E> genericType) {
        this.repository = repository;
        this.genericType = genericType;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public D save(D d) {
        try {
            D save = mapper.toDto(repository.save(mapper.toEntity(d)));
            log.info(this.genericType.getSimpleName() + " save");
            return save;
        } catch (Exception ex) {
            log.error(this.genericType.getSimpleName() + " don't save");
            throw new SaveEntityException(this.genericType.getSimpleName());
        }
    }

    @Override
    public D getById(Long id) {
        Optional<E> entity = this.repository.findById(id);
        if (entity.isPresent()) {
            log.info(this.genericType.getSimpleName() + " get, id: " + id);
            return mapper.toDto(entity.get());
        }
        log.error(this.genericType.getSimpleName() + " don't get, id: " + id);
        throw new EntityNotFoundException(this.genericType.getSimpleName());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            this.repository.deleteById(id);
            log.info(this.genericType.getSimpleName() + " delete, id: " + id);
        } catch (Exception ex) {
            log.error(this.genericType.getSimpleName() + " don't delete, id: " + id
                    + " Error message: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public D update(Long id, D d) {
        mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(this.genericType.getSimpleName())));
        try {
            d.setId(id);
            this.repository.save(mapper.toEntity(d));
            log.info(this.genericType.getSimpleName() + " update");
            return d;
        } catch (Exception ex) {
            log.error(this.genericType.getSimpleName() + " don't update");
            throw new SaveEntityException(this.genericType.getSimpleName());
        }
    }

    @Override
    public Page<D> findAll(Integer pageNumber, String sortField, String sortDirection) {
        Pageable pageable = findAllWithPagination(pageNumber, sortField, sortDirection);
        Page<D> all = repository.findAll(pageable).map(mapper::toDto);
        log.info("Page " + this.genericType.getSimpleName() + " create");
        return all;
    }

    public Pageable findAllWithPagination(final Integer pageNumber,
                                          final String sortField, final String sortDirection) {
        final int pageSize = 3;
        PageRequest pageRequest;
//                = PageRequest.of(pageNumber - 1, pageSize);
//        if (sortField != null && sortField.equalsIgnoreCase("sort")) {
//            pageRequest = PageRequest.of(pageNumber - 1, pageSize);
//            log.info(this.genericType.getSimpleName() + " sort by: " + sortField);
//        } else if (sortField != null && sortDirection != null) {
            Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() : Sort.by(sortField).descending();
            pageRequest = PageRequest.of(pageNumber - 1, pageSize, sort);
            log.info(this.genericType.getSimpleName() + " sort by: " + sortField +
                    " ,sort direction: " + sortDirection);
//        }
        return pageRequest;
    }
}


