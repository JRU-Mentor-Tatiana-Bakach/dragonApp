package com.javarush.dragonapp.service.impl;

import com.javarush.dragonapp.dto.UserDTO;
import com.javarush.dragonapp.exception.EntityNotFoundException;
import com.javarush.dragonapp.mapper.UserMapper;
import com.javarush.dragonapp.model.User;
import com.javarush.dragonapp.model.enums.Role;
import com.javarush.dragonapp.repository.UserRepository;
import com.javarush.dragonapp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl extends CrudServiceImpl<User, UserDTO, UserMapper, UserRepository> implements UserService{

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        super(repository, mapper, User.class);
    }

    @Override
    public Optional<UserDTO> findUserByUserName(String name) {
        return Optional.ofNullable(repository.findUserByUserName(name).map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(User.class.getSimpleName())));
    }

    @Override
    public Page<UserDTO> findUserByRole(Role role, Integer pageNumber, String sortField, String sortDirection) {
        Pageable pageable = findAllWithPagination(pageNumber, sortField, sortDirection);
        return repository.findUserByRole(role, pageable).map(mapper::toDto);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByUserName(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUserName(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
