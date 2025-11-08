package com.example.weather.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather_records")
public class WeatherRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String description;

    private Double temperature;   // nullable для безопасной миграции
    private Double windSpeed;
    private Integer humidity;
    private String icon;

    private LocalDateTime createdAt = LocalDateTime.now();

    public WeatherRecord() {}

    public WeatherRecord(String city, String description, Double temperature,
                         Double windSpeed, Integer humidity, String icon) {
        this.city = city;
        this.description = description;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.icon = icon;
    }

    // --- Геттеры и сеттеры ---
    public Long getId() { return id; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }

    public Double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(Double windSpeed) { this.windSpeed = windSpeed; }

    public Integer getHumidity() { return humidity; }
    public void setHumidity(Integer humidity) { this.humidity = humidity; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}