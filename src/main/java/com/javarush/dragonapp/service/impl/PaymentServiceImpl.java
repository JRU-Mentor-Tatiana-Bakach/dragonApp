package com.javarush.dragonapp.service.impl;

import com.javarush.dragonapp.dto.PaymentDTO;
import com.javarush.dragonapp.mapper.PaymentMapper;
import com.javarush.dragonapp.model.Payment;
import com.javarush.dragonapp.repository.PaymentRepository;
import com.javarush.dragonapp.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends CrudServiceImpl<Payment, PaymentDTO, PaymentMapper,
        PaymentRepository> implements PaymentService {

    protected PaymentServiceImpl(PaymentRepository repository, PaymentMapper mapper) {
        super(repository, mapper, Payment.class);
    }
}
