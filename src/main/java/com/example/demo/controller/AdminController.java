package com.example.demo.controller;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping()
    public String index() {
        return "redirect:/admin/";
    }

    @RequestMapping("/")
    public String admin() {
        return "Admin/admin.dashboard";
    }

    @GetMapping("/show-movie")
    public String addMovie() {
        return "Admin/admin.movie.html";
    }

    @GetMapping("/show-user")
    public String showUser(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "Admin/admin.dataTable.html";
    }

    @GetMapping("/search-user")
    public String searchUser(Model model, @RequestParam String keyword) {
        List<UserEntity> users = userService.searchUser(keyword, keyword, keyword);
        model.addAttribute("users", users);
        return "Admin/admin.dataTable.html";
    }

    @GetMapping("/show-all")
    public String showTheatre() {
        return "Admin/admin.basicTable.html";
    }

    @GetMapping("/snacks")
    public String snacks() {
        return "Admin/admin.snacks.html";
    }

    @GetMapping("/basicTable")
    public String basicTable() {
        return "Admin/admin.basicTable.html";
    }

    @GetMapping("/dataTable")
    public String dataTable() {
        return "Admin/admin.dataTable.html";
    }
}
