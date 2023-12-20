package com.javarush.dragonapp.mapper;

import com.javarush.dragonapp.dto.DragonDTO;
import com.javarush.dragonapp.dto.PaymentDTO;
import com.javarush.dragonapp.model.Dragon;
import com.javarush.dragonapp.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper extends BaseMapper<Payment, PaymentDTO> {

    @Autowired
    PaymentMapper() {
        super(Payment.class, PaymentDTO.class);
    }
}
