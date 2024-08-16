package com.example.demo.controller;


import com.example.demo.model.entity.Schedule;
import com.example.demo.model.entity.Seat;
import com.example.demo.model.entity.Ticket;
import com.example.demo.service.ScheduleService;
import com.example.demo.service.SeatService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private final ScheduleService scheduleService;
    private final SeatService seatService;

    @PostMapping("/buy-ticket")
    public String buyTicket(@RequestParam("seats") String seats,
                            @RequestParam("scheduleId") Long scheduleId,
                            Model model,
                            RedirectAttributes ra) {
        Optional<Schedule> schedule = scheduleService.getSchedule(scheduleId);
        if (schedule.isEmpty()) {
            return "redirect:/home";
        }
        List<Seat> seatList = schedule.get().getRoom().getSeat();
        String[] seat = seats.split(",");
        boolean isAvailable = seatService.areSeatsAvailable(seatList, seat);
        if (!isAvailable) {
            ra.addFlashAttribute("message", "Some seats are unavailable or do not exist.");
            return "redirect:/home/movie/schedule/"+scheduleId+"/select-seat";
        }
        int numberOfSeats = seat.length;
        Long price = numberOfSeats * schedule.get().getMovie().getPrice();
        Ticket ticket = Ticket.;
        return "redirect:/ticket/history";
    }

    @GetMapping("/history")
    public String history(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String && "anonymousUser".equals(authentication.getPrincipal()));

        if (isAuthenticated) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                model.addAttribute("username", userDetails.getUsername());
                if (userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"))) {
                    return "redirect:/admin";
                }
            }
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
        return "layout/detail";
    }
}
