package com.example.demo.controller;

import com.example.demo.model.response.TicketResponse;
import com.example.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/staff")
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping("/get-film-by-id/{id}")
    public ResponseEntity<TicketResponse> getFilmById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findTicketById(id));
    }

    @GetMapping("/check-payment/{id}")
    public ResponseEntity<Boolean> checkPayment(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.checkPayment(id));
    }

    @PostMapping("/payment-ticket/{id}")
    public ResponseEntity<TicketResponse> paymentTicket(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.paymentTicket(id));
    }

    @GetMapping("/ticket")
    public ResponseEntity<List<TicketResponse>> getAllTicket() {
        return ResponseEntity.ok(employeeService.getAllTicket());
    }
}
