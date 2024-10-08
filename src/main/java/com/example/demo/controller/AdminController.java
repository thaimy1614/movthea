package com.example.demo.controller;

import com.example.demo.model.entity.Movie;
import com.example.demo.model.entity.Ticket;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.service.FileSystemStorageService;
import com.example.demo.service.MovieService;
import com.example.demo.service.TicketService;
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
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final MovieService movieService;
    private final FileSystemStorageService fileService;
    private final TicketService ticketService;

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

    @GetMapping("/show-all-movies")
    public String showAllMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies(null);
        model.addAttribute("movies", movies);
        return "Admin/admin.searchMovie.html";
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
            String fileName = "";
            if (file.isEmpty()) {
                if (movieService.getMovie(movie.getId()).isPresent()) {
                    fileName = movieService.getMovie(movie.getId()).get().getImageLink();
                }
            } else {
                fileName = fileService.store(file);
            }

            movie.setImageLink(fileName);

            if (movieService.addMovie(movie)) {
                redirectAttributes.addFlashAttribute("message", "Movie saved successfully!");
                return "redirect:/admin/show-all-movies";
            } else {
                redirectAttributes.addFlashAttribute("message", "Failed to save movie");
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

    @GetMapping("/update-movie/{id}")
    public String updateMovie(Model model, @PathVariable Long id, RedirectAttributes ra) {
        Optional<Movie> movie = movieService.getMovie(id);
        if (movie.isEmpty()) {
            ra.addFlashAttribute("message", "Movie not found!");
        } else {
            model.addAttribute("movie", movie.get());
        }
        return "Admin/admin.updateMovie.html";
    }

    @PostMapping("/update-movie")
    public String updateMovie(@ModelAttribute("movie") Movie movie, RedirectAttributes redirectAttributes) {
        movieService.addMovie(movie);
        redirectAttributes.addAttribute("message", "Movie updated successfully!");
        return "redirect:/admin/add-movie";
    }

    @GetMapping("/delete-movie/{id}")
    public String deleteMovie(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Movie movie = movieService.getMovie(id).orElseThrow();
        if (
                !movie.getImageLink().isEmpty()
        ) {
            try {
                fileService.delete(movie.getImageLink());
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("message", "Failed to delete image");
                return "redirect:/admin/show-all-movies";
            }

        }
        movieService.deleteMovie(id);
        redirectAttributes.addAttribute("message", "Movie deleted successfully!");
        return "redirect:/admin/show-all-movies";
    }

    @GetMapping("/ticket")
    public String showTicket(Model model) {
        List<Ticket> ticketList = ticketService.getAllTickets();
        model.addAttribute("tickets", ticketList);
        return "Admin/ticket";
    }

    @GetMapping("/ticket/reject/{id}")
    public String showTicketReject(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ticketService.reject(id);
        redirectAttributes.addFlashAttribute("message", "Ticket rejected successfully!");
        return "redirect:/admin/ticket";
    }

    @GetMapping("/ticket/confirm/{id}")
    public String showTicketConfirm(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ticketService.confirm(id);
        redirectAttributes.addFlashAttribute("message", "Ticket confirmed successfully!");
        return "redirect:/admin/ticket";
    }
}
