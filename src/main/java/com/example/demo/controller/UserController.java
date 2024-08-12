package com.example.demo.controller;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public String updateProfile(@ModelAttribute("user") UserEntity user, @RequestParam Long id) {
        userService.updateProfile(id, user);
        return "redirect:/myAccount";
    }
}
