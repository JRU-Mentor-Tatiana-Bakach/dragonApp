package com.javarush.dragonapp.repository;

import com.javarush.dragonapp.model.User;
import com.javarush.dragonapp.model.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User>{

    Optional<User> findUserByUserName(String name);

    List<User> findUserByUserInfoIsNull();

    Page<User> findUserByRole(Role role, Pageable pageable);
}
