package com.example.demo.controller;

import com.example.demo.model.entity.Theatre;
import com.example.demo.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/theatre")
public class TheatreController {
    private final TheatreService theatreService;

    @GetMapping("/show-theatre")
    public String showTheatre(Model model) {
        List<Theatre> theatreList = theatreService.getAllTheatres();
        model.addAttribute("theatres", theatreList);
        return "Admin/admin.showTheatre.html";
    }

    @GetMapping("/add-theatre")
    public String addTheatre(Model model) {
        model.addAttribute("theatre", new Theatre());
        return "Admin/admin.addTheatre";
    }

    @PostMapping("/add-theatre")
    public String addTheatre(@ModelAttribute("theatre") Theatre theatre,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Failed validation");
            return "redirect:/admin/theatre/add-theatre";
        }

        if (theatreService.addTheatre(theatre)) {
            redirectAttributes.addFlashAttribute("message", "Theatre saved successfully!");
            return "redirect:/admin/theatre/show-theatre";
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to save theatre");
            return "redirect:/admin/theatre/add-theatre";
        }
    }

    @GetMapping("/search-theatre")
    public String searchTheatre() {
        return "Admin/admin.searchTheatre.html";
    }

    @GetMapping("/update-theatre/{id}")
    public String updateTheatre(Model model, @PathVariable Long id, RedirectAttributes ra) {
        Optional<Theatre> theatre = theatreService.getTheatre(id);
        if (theatre.isEmpty()) {
            ra.addFlashAttribute("message", "Theatre not found!");
        } else {
            model.addAttribute("theatre", theatre.get());
        }
        return "Admin/admin.updateTheatre.html";
    }

    @PostMapping("/update-theatre")
    public String updateTheatre(@ModelAttribute("theatre") Theatre theatre, RedirectAttributes redirectAttributes) {
        theatreService.addTheatre(theatre);
        redirectAttributes.addFlashAttribute("message", "Theatre updated successfully!");
        return "redirect:/admin/theatre/add-theatre";
    }

    @GetMapping("/delete-theatre/{id}")
    public String deleteTheatre(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        theatreService.deleteTheatre(id);
        redirectAttributes.addFlashAttribute("message", "Theatre deleted successfully!");
        return "redirect:/admin/theatre/show-theatre";
    }
}
