package com.javarush.dragonapp.mapper;

import com.javarush.dragonapp.dto.FlightDTO;
import com.javarush.dragonapp.model.Dragon;
import com.javarush.dragonapp.model.Flight;
import com.javarush.dragonapp.repository.DragonRepository;
import com.javarush.dragonapp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FlightMapper extends BaseMapper<Flight, FlightDTO>{

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final DragonRepository dragonRepository;

    @Autowired
    public FlightMapper(ModelMapper mapper, UserRepository userRepository,
                        DragonRepository dragonRepository) {
        super(Flight.class, FlightDTO.class);
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.dragonRepository = dragonRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Flight.class, FlightDTO.class)
                .implicitMappings()
                .addMappings(m -> m.skip(FlightDTO::setUserId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(FlightDTO::setDragonId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(FlightDTO.class, Flight.class)
                .implicitMappings()
                .addMappings(m -> m.skip(Flight::setUser)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Flight::setDragon)).setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(Flight source, FlightDTO destination) {
        destination.setUserId(getIdUser(source));
        destination.setDragonId(getIdDragon(source));
    }

    private Long getIdUser(Flight source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getUser().getId();
    }
    private Long getIdDragon(Flight source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getDragon().getId();
    }

    @Override
    void mapSpecificFields(FlightDTO source, Flight destination) {
        destination.setUser(userRepository.findById(source.getUserId()).orElse(null));
        destination.setDragon(dragonRepository.findById(source.getDragonId()).orElse(null));

    }
}
