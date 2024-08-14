package com.example.demo.controller;

import com.example.demo.model.entity.Schedule;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/schedule")
public class ScheduleController {
    private static final Logger log = LoggerFactory.getLogger(ScheduleController.class);
    private final ScheduleService scheduleService;
    private final MovieService movieService;
    private final TheatreService theatreService;
    private final SlotService slotService;
    private final RoomService roomService;

    @GetMapping("/show-schedule")
    public String showSchedule(Model model) {
        List<Schedule> scheduleList = scheduleService.getAllSchedules();
        model.addAttribute("schedules", scheduleList);
        return "Admin/admin.showSchedule.html";
    }

    @GetMapping("/add-schedule")
    public String addSchedule(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("movies", movieService.getAllMovies(null));
        model.addAttribute("theatres", theatreService.getAllTheatres());
        model.addAttribute("slots", slotService.getAllSlots());
        model.addAttribute("rooms", roomService.getAllRooms());
        return "Admin/admin.addSchedule";
    }

    @PostMapping("/add-schedule")
    public String addSchedule(@ModelAttribute("schedule") Schedule schedule,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Failed validation");
            return "redirect:/admin/schedule/add-schedule";
        }

        if (scheduleService.addSchedule(schedule)) {
            redirectAttributes.addFlashAttribute("message", "Schedule saved successfully!");
            return "redirect:/admin/schedule/show-schedule";
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to save schedule");
            return "redirect:/admin/schedule/add-schedule";
        }
    }

    @GetMapping("/search-schedule")
    public String searchSchedule() {
        return "Admin/admin.searchSchedule.html";
    }

    @GetMapping("/update-schedule/{id}")
    public String updateSchedule(Model model, @PathVariable Long id, RedirectAttributes ra) {
        Optional<Schedule> schedule = scheduleService.getSchedule(id);
        if (schedule.isEmpty()) {
            ra.addFlashAttribute("message", "Schedule not found!");
        } else {
            Schedule s = schedule.get();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            s.setStartAtFormatted(s.getStartAt().format(formatter));

            model.addAttribute("movies", movieService.getAllMovies(null));
            model.addAttribute("theatres", theatreService.getAllTheatres());
            model.addAttribute("slots", slotService.getAllSlots());
            model.addAttribute("rooms", roomService.getAllRooms());
            model.addAttribute("schedule", s);
//            log.info(s.getStartAtFormatted());

        }
        return "Admin/admin.updateSchedule.html";
    }

    @PostMapping("/update-schedule")
    public String updateSchedule(@ModelAttribute("schedule") Schedule schedule, RedirectAttributes redirectAttributes) {
        scheduleService.addSchedule(schedule);
        redirectAttributes.addFlashAttribute("message", "Schedule updated successfully!");
        return "redirect:/admin/schedule/add-schedule";
    }

    @GetMapping("/delete-schedule/{id}")
    public String deleteSchedule(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        scheduleService.deleteSchedule(id);
        redirectAttributes.addFlashAttribute("message", "Schedule deleted successfully!");
        return "redirect:/admin/schedule/show-schedule";
    }
}
