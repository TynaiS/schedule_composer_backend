package com.example.schedule_composer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;

@Entity
@Table(name = ScheduleLunchItem.TABLE_NAME)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleLunchItem {

    public static final String TABLE_NAME = "schedule_lunch_item";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_version_id", nullable = false)
    private ScheduleVersion scheduleVersion;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private Group group;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek day;

    @ManyToMany
    @JoinTable(
            name = TABLE_NAME + "_time_slot",
            joinColumns = @JoinColumn(name = TABLE_NAME + "_id"),
            inverseJoinColumns = @JoinColumn(name = "time_slot_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {TABLE_NAME + "_id", "time_slot_id"})

    )
    private List<TimeSlot> timeSlots;
}
