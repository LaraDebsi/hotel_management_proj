package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public boolean processPayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setRentingId(paymentRequest.getRentingId());
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentDate(LocalDate.now());
        paymentRepository.save(payment);
        return true;
    }
}
