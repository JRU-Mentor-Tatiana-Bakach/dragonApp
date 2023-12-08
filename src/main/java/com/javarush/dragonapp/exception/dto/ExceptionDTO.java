package com.javarush.dragonapp.exception.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionDTO {

    private String error;
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;

}
