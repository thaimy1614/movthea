package com.example.demo.controller;

import com.example.demo.model.entity.Payment;
import com.example.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public String payment(Model model) {
        List<Payment> paymentList = paymentService.getAllPayment();
        model.addAttribute("paymentList", paymentList);
        return "Admin/payment";
    }
}
