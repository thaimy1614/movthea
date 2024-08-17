package com.example.demo.controller;

import com.example.demo.model.entity.Movie;
import com.example.demo.model.entity.Schedule;
import com.example.demo.model.entity.Seat;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.service.MovieService;
import com.example.demo.service.ScheduleService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final MovieService movieService;
    private final ScheduleService scheduleService;

    @GetMapping("/home")
    public String home(Model model) {
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

        List<Movie> nowShowing = movieService.getAllMovies(Movie.MovieStatus.NOW_SHOWING);
        List<Movie> comingSoon = movieService.getAllMovies(Movie.MovieStatus.COMING_SOON);
        List<Movie> trending = movieService.getAllMovies(Movie.MovieStatus.TRENDING);
        model.addAttribute("nowShowing", nowShowing);
        model.addAttribute("comingSoon", comingSoon);
        model.addAttribute("trending", trending);
        return "layout/index";
    }

    @GetMapping("/myAccount")
    public String myAccount(Model model) {
        UserEntity user = new UserEntity();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            user = userService.findByUsername(userDetails.getUsername());
            model.addAttribute("username", userDetails.getUsername());
        }
        model.addAttribute("user", user);
        model.addAttribute("fullName", user.getName());
        model.addAttribute("email", user.getEmail());
        return "Profile/myAccount";
    }

    @GetMapping("/home/movie/{id}/select-time")
    public String selectTime(@PathVariable Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String && "anonymousUser".equals(authentication.getPrincipal()));
        model.addAttribute("isAuthenticated", isAuthenticated);
        Movie movie = movieService.getMovie(id).orElse(new Movie());
        List<Schedule> schedule = scheduleService.getSchedulesByMovie(movie);
        model.addAttribute("schedule", schedule);
        return "layout/Event_Schedule_list";
    }

    @GetMapping("/home/movie/select-time")
    public String selectTime(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String && "anonymousUser".equals(authentication.getPrincipal()));
        model.addAttribute("isAuthenticated", isAuthenticated);
        List<Schedule> schedule = scheduleService.getSchedulesByStartAtFromNow();
        model.addAttribute("schedule", schedule);
        return "layout/Event_Schedule_list";
    }

    @GetMapping("/home/movie/schedule/{scheduleId}/select-seat")
    public String selectSeat(@PathVariable Long scheduleId, Model model) {
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
        Optional<Schedule> schedule = scheduleService.getSchedule(scheduleId);
        if(schedule.isEmpty()){
            return "redirect:/home";
        }
        model.addAttribute("schedule", schedule.get());
        List<Seat> seats = schedule.get().getRoom().getSeat();
        Map<Character, List<Seat>> groupedSeats = seats.stream()
                .collect(Collectors.groupingBy(seat -> seat.getSeatCode().charAt(0)));

        model.addAttribute("groupedSeats", groupedSeats);
        return "layout/seats";
    }

}
