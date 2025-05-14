package com.example.schedule_composer.entity;

import com.example.schedule_composer.utils.types.TeachingMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;

@Entity
@Table(name = ScheduleItem.TABLE_NAME)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleItem {

    public static final String TABLE_NAME = "schedule_item";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "setup_id", referencedColumnName = "id")
    private Setup setup;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Column(nullable = false)
    private DayOfWeek day;

    @ManyToMany
    @JoinTable(
            name = TABLE_NAME + "_time_slots",
            joinColumns = @JoinColumn(name = TABLE_NAME + "_id"),
            inverseJoinColumns = @JoinColumn(name = "time_slot_id")
    )
    private List<TimeSlot> timeSlots;

    @Enumerated(EnumType.STRING)
    private TeachingMode teachingMode;
}
