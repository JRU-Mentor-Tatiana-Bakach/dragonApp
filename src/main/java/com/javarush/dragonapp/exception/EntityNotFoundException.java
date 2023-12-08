package com.javarush.dragonapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EntityNotFoundException extends RuntimeException{

    private Long entityId;

    private String entityClass;

}
