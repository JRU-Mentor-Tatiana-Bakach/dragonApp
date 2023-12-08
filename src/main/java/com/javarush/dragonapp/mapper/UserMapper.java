package com.javarush.dragonapp.mapper;

import com.javarush.dragonapp.dto.UserDTO;
import com.javarush.dragonapp.model.User;
import com.javarush.dragonapp.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper extends BaseMapper<User, UserDTO> {

    private final ModelMapper mapper;
    private final UserInfoRepository unicornRepository;

    @Autowired
    public UserMapper(ModelMapper mapper, UserInfoRepository userInfoRepository) {
        super(User.class, UserDTO.class);
        this.mapper = mapper;
        this.unicornRepository = userInfoRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(m -> m.skip(UserDTO::setUserInfoId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(UserDTO.class, User.class)
                .addMappings(m -> m.skip(User::setUserInfo)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(User source, UserDTO destination) {
        destination.setUserInfoId(getIdUserInfo(source));
    }

    private Long getIdUserInfo(User source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getUserInfo().getId();
    }

    @Override
    void mapSpecificFields(UserDTO source, User destination) {
        destination.setUserInfo(unicornRepository.findById(source.getUserInfoId()).orElse(null));
    }
}
