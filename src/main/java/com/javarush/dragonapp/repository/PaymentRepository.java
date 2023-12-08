package com.javarush.dragonapp.repository;

import com.javarush.dragonapp.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface PaymentRepository extends BaseRepository<Payment>{

    Page<Payment> findPaymentByDateOfPaymentBefore(LocalDate date, Pageable pageable);

    Page<Payment> findPaymentByDateOfPaymentAfter(LocalDate date, Pageable pageable);
}
