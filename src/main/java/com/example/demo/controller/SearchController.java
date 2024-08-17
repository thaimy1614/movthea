package com.example.demo.controller;

import com.example.demo.model.entity.Movie;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home/search")
public class SearchController {
    private final MovieService movieService;

    @GetMapping()
    public List<Movie> search(@RequestParam String keyword) {
        return movieService.searchMovie(keyword);
    }
}
