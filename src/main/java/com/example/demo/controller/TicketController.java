package com.example.demo.controller;


import com.example.demo.model.entity.Schedule;
import com.example.demo.model.entity.Seat;
import com.example.demo.model.entity.Ticket;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private final ScheduleService scheduleService;
    private final SeatService seatService;
    private final TicketService ticketService;
    private final UserService userService;
    private final RoomService roomService;

    @PostMapping("/buy-ticket")
    public String buyTicket(@RequestParam("seats") String seats,
                            @RequestParam("scheduleId") Long scheduleId,
                            Model model,
                            RedirectAttributes ra) {
        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String && "anonymousUser".equals(authentication.getPrincipal()));

        if (isAuthenticated) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                username = userDetails.getUsername();
                if (userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"))) {
                    return "redirect:/admin";
                }
            }
        }
        Optional<Schedule> schedule = scheduleService.getSchedule(scheduleId);
        if (schedule.isEmpty()) {
            return "redirect:/home";
        }
        List<Seat> seatList = schedule.get().getRoom().getSeat();
        String[] seat = seats.split(",");
        List<Seat> bookedSeat = seatService.areSeatsAvailable(seatList, seat);
        if (bookedSeat==null || bookedSeat.isEmpty()) {
            ra.addFlashAttribute("message", "Some seats are unavailable or do not exist.");
            return "redirect:/home/movie/schedule/" + scheduleId + "/select-seat";
        }
        int numberOfSeats = seat.length;
        Long totalPrice = numberOfSeats * schedule.get().getMovie().getPrice();
        Ticket ticket = Ticket.builder()
                .user(userService.findByUsername(username))
                .numberSeats(numberOfSeats)
                .purchaseDate(LocalDateTime.now())
                .schedule(schedule.get())
                .seats(seats)
                .totalPrice(totalPrice)
                .status(Ticket.Status.PENDING)
                .build();
        ticketService.addTicket(ticket);
        seatService.disableSeats(bookedSeat);
        return "redirect:/ticket/history";
    }

    @GetMapping("/history")
    public String history(Model model) {
        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String && "anonymousUser".equals(authentication.getPrincipal()));

        if (isAuthenticated) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                username = userDetails.getUsername();
                if (userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"))) {
                    return "redirect:/admin";
                }
            }
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
        List<Ticket> tickets = ticketService.getAllTicketsByUsername(username);
        model.addAttribute("tickets", tickets);
        return "layout/detail";
    }
}
