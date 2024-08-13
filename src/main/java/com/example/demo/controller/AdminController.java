package com.example.demo.controller;

import com.example.demo.model.entity.Movie;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.service.FileSystemStorageService;
import com.example.demo.service.MovieService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final MovieService movieService;
    private final FileSystemStorageService fileService;

    @GetMapping()
    public String index() {
        return "redirect:/admin/";
    }

    @RequestMapping("/")
    public String admin() {
        return "Admin/admin.dashboard";
    }

    @GetMapping("/show-movie")
    public String showMovie() {
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

    @GetMapping("/add-movie")
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "Admin/admin.addMovie";
    }

    @PostMapping("/add-movie")
    public String addMovie(@ModelAttribute("movie") Movie movie,
                           @RequestParam("file-input") MultipartFile file,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Failed validation");
            return "redirect:/admin/add-movie";
        }

        try {
            String fileName = fileService.store(file);

            movie.setImageLink(fileName);

            if (movieService.addMovie(movie)) {
                return "redirect:/admin/add-movie";
            } else {
                redirectAttributes.addFlashAttribute("message", "Failed to save product");
                return "redirect:/admin/add-movie";
            }
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Failed to upload image");
            return "redirect:/admin/add-movie";
        }
//        return "redirect:/admin/add-movie";
    }

    @GetMapping("/search-movie")
    public String searchMovie() {
        return "Admin/admin.searchMovie.html";
    }

    @GetMapping("/update-movie")
    public String updateMovie(Model model, @RequestParam int id) {
        return "Admin/admin.addMovie.html";
    }

    @PostMapping("/update-movie/{id}")
    public String updateMovie(@PathVariable Long id, @ModelAttribute Movie movie, RedirectAttributes redirectAttributes) {
        movieService.addMovie(movie);
        redirectAttributes.addAttribute("msg", "Movie updated successfully!");
        return "redirect:/admin/add-movie";
    }

    @PostMapping("/delete-movie/{id}")
    public String deleteMovie(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        movieService.deleteMovie(id);
        redirectAttributes.addAttribute("msg", "Movie deleted successfully!");
        return "Admin/admin.addMovie.html";
    }
}
