package com.example.weather.service;

import com.example.weather.entity.WeatherRecord;
import com.example.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weatherstack.api.key}")
    private String apiKey;

    public WeatherRecord fetchAndSaveWeather(String city) {
        String url = "http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + city;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response.containsKey("error")) {
            throw new RuntimeException("Weatherstack API error: " + response.get("error"));
        }

        Map<String, Object> current = (Map<String, Object>) response.get("current");
        List<String> descriptions = (List<String>) current.get("weather_descriptions");
        List<String> icons = (List<String>) current.get("weather_icons");

        double temperature = ((Number) current.get("temperature")).doubleValue();
        double windSpeed = ((Number) current.get("wind_speed")).doubleValue();
        int humidity = ((Number) current.get("humidity")).intValue();
        String description = descriptions.get(0);
        String icon = icons.get(0);

        WeatherRecord record = new WeatherRecord(city, description, temperature, windSpeed, humidity, icon);
        return repository.save(record);
    }

    public List<WeatherRecord> getAll() {
        return repository.findAll();
    }
}