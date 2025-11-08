package com.example.weather.repository;

import com.example.weather.entity.WeatherRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherRecord, Long> {}