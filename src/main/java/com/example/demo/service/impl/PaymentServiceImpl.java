package com.example.demo.service.impl;

import com.example.demo.model.entity.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    @Override
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }
}
