package com.javarush.dragonapp.mapper;

import com.javarush.dragonapp.dto.DragonDTO;
import com.javarush.dragonapp.model.Dragon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DragonMapper extends BaseMapper<Dragon, DragonDTO>{

    @Autowired
    public DragonMapper(){
        super(Dragon.class, DragonDTO.class);
    }
}
