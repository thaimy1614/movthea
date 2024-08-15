package com.example.demo.controller;


import com.example.demo.model.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

    @PostMapping("/buy-ticket")
    public String buyTicket(@RequestParam("seats") String seats,
                            @RequestParam("scheduleId") Long scheduleId,
                            Model model) {

        log.info(seats + " " + scheduleId);
        return "redirect:/home";
    }
}
