package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping()
    public String index(){
        return "redirect:/admin/";
    }

    @RequestMapping("/")
    public String admin() {
        return "Admin/admin.dashboard";
    }

    @GetMapping("/addMovie")
    public String addMovie() {return "Admin/admin.movie.html";}

    @GetMapping("/userManage")
    public String userManage() {return "Admin/admin.user.html";}

    @GetMapping("/theatre")
    public String theatre() {return "Admin/admin.theatre.html";}

    @GetMapping("/snacks")
    public String snacks() {return "Admin/admin.snacks.html";}

    @GetMapping("/basicTable")
    public String basicTable() {return "Admin/admin.basicTable.html";}

    @GetMapping("/dataTable")
    public String dataTable() {return "Admin/admin.dataTable.html";}
}
