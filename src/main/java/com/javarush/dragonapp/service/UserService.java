package com.javarush.dragonapp.service;

import com.javarush.dragonapp.dto.UserDTO;
import com.javarush.dragonapp.model.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends CrudService<UserDTO>,
        UserDetailsService
{
    Optional<UserDTO> findUserByUserName(String name);

    Page<UserDTO> findUserByRole(Role role, Integer pageNumber,
                              String sortField, String sortDirection);
}
