package com.example.schedule_composer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleLunch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private Group group;

    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    private LocalTime startTime;
    private LocalTime endTime;
}
