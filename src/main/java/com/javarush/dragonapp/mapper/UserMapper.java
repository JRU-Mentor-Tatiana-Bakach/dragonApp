package com.javarush.dragonapp.mapper;

import com.javarush.dragonapp.dto.UserDTO;
import com.javarush.dragonapp.model.User;
import com.javarush.dragonapp.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Component
public class UserMapper extends BaseMapper<User, UserDTO> {

    private final ModelMapper mapper;
    private final UserInfoRepository userInfoRepository;

    private final PasswordEncoder passwordEncoder;

    public UserMapper(ModelMapper mapper, UserInfoRepository unicornRepository,
                      PasswordEncoder passwordEncoder)
    {
        super(User.class, UserDTO.class);
        this.mapper = mapper;
        this.userInfoRepository = unicornRepository;
        this.passwordEncoder = passwordEncoder;
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
        destination.setUserInfo(userInfoRepository.findById(source.getUserInfoId()).orElse(null));
        Optional.ofNullable(source.getPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(destination::setPassword);
    }
}
