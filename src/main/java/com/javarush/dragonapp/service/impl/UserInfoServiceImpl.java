package com.javarush.dragonapp.service.impl;

import com.javarush.dragonapp.dto.UserInfoDTO;
import com.javarush.dragonapp.mapper.UserInfoMapper;
import com.javarush.dragonapp.model.UserInfo;
import com.javarush.dragonapp.repository.UserInfoRepository;
import com.javarush.dragonapp.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends CrudServiceImpl<UserInfo, UserInfoDTO, UserInfoMapper,
        UserInfoRepository> implements UserInfoService {

    protected UserInfoServiceImpl(UserInfoRepository repository, UserInfoMapper mapper) {
        super(repository, mapper, UserInfo.class);
    }
}
