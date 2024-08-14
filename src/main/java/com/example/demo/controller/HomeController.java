package com.example.demo.controller;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

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


    @GetMapping("/buyTicket")
    public String buyTicket() {
        return "layout/SelectTime.html";
    }
}
