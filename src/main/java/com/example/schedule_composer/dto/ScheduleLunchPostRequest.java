package com.example.schedule_composer.dto;

import com.example.schedule_composer.entity.Group;
import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class ScheduleLunchPostRequest {

    private Long id;
    private Long groupId;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
}
