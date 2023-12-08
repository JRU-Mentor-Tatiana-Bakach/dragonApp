package com.javarush.dragonapp.repository;

import com.javarush.dragonapp.model.UserInfo;
import com.javarush.dragonapp.model.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface UserInfoRepository extends BaseRepository<UserInfo>{

    Page<UserInfo> findUserInfoByDateOfBirthBetweenOrPhoneNumberContaining
            (LocalDate dateStart, LocalDate dateEnd, String phoneNumberContain, Pageable pageable);

    UserInfo findUserInfoByEmail(String email);

    Page<UserInfo> findUserInfoByRole(Role role, Pageable pageable);
}
