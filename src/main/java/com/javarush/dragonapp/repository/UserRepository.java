package com.javarush.dragonapp.repository;

import com.javarush.dragonapp.model.User;

import java.util.List;

public interface UserRepository extends BaseRepository<User>{

    User findUserByUserName(String name);

    List<User> findUserByUserInfoIsNull();
}
