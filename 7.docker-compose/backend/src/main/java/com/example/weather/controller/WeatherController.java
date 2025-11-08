package com.example.weather.controller;

import com.example.weather.entity.WeatherRecord;
import com.example.weather.service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @PostMapping("/{city}")
    public WeatherRecord fetch(@PathVariable String city) {
        return service.fetchAndSaveWeather(city);
    }

    @GetMapping
    public List<WeatherRecord> all() {
        return service.getAll();
    }
}