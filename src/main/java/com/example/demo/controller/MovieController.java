package com.example.demo.controller;

import com.example.demo.model.entity.Movie;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home/movie")
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/{id}")
    public String showMovie(@PathVariable Long id, Model model) {
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
        Movie movie = movieService.getMovie(id).orElse(new Movie());
        model.addAttribute("movie", movie);
        return "layout/product-page";
    }
}
