package com.example.demo.controller;

import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "layout/Login.html";
    }

    @GetMapping("/signup-page")
    public String showSignupPage() {
        return "layout/Signup.html";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "layout/Signup";
        }
        try {
            userService.createUser(username, password);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred. Please try again.");
            return "layout/Signup";
        }
    }

    @GetMapping("/change-password")
    public String showChangePasswordPage() {
        return "layout/change-password.html";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String password, @RequestParam("confirmPassword") String confirmPassword, Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "layout/Signup";
        }

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();
            String username = ((UserDetails) principal).getUsername();
            userService.changePassword(username, oldPassword, password);
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred. Please try again.");
            return "redirect:/login";
        }
    }


}
