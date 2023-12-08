package com.javarush.dragonapp.mapper;

import com.javarush.dragonapp.dto.UserInfoDTO;
import com.javarush.dragonapp.model.UserInfo;
import com.javarush.dragonapp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserInfoMapper extends BaseMapper<UserInfo, UserInfoDTO> {

    private final ModelMapper mapper;
    private final UserRepository userRepository;

    @Autowired
    public UserInfoMapper(ModelMapper mapper, UserRepository userRepository) {
        super(UserInfo.class, UserInfoDTO.class);
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(UserInfo.class, UserInfoDTO.class)
                .addMappings(m -> m.skip(UserInfoDTO::setUserId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(UserInfoDTO.class, UserInfo.class)
                .addMappings(m -> m.skip(UserInfo::setUser)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(UserInfo source, UserInfoDTO destination) {
        destination.setUserId(getIdUser(source));
    }

    private Long getIdUser(UserInfo source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getUser().getId();
    }

    @Override
    void mapSpecificFields(UserInfoDTO source, UserInfo destination) {
        destination.setUser(userRepository.findById(source.getUserId()).orElse(null));
    }
}
