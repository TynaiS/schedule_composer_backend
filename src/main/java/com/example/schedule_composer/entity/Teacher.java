package com.example.schedule_composer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer dailyHours;
    private Integer weeklyHours;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getDailyHours() {
        return dailyHours;
    }

    public Integer getWeeklyHours() {
        return weeklyHours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDailyHours(Integer dailyHours) {
        this.dailyHours = dailyHours;
    }

    public void setWeeklyHours(Integer weeklyHours) {
        this.weeklyHours = weeklyHours;
    }
}
