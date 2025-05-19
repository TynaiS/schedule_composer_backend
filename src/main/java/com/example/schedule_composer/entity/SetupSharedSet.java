package com.example.schedule_composer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "setup_shared_set")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetupSharedSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_version_id", nullable = false)
    private ScheduleVersion scheduleVersion;

    private String name;

    @Column(name = "hours_a_week")
    private Integer hoursAWeek;

}
