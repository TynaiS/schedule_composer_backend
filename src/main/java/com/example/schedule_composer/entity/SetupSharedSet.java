package com.example.schedule_composer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "setup_shared_set",
    uniqueConstraints = @UniqueConstraint(name = "unique_setup_shared_set_name", columnNames = {"schedule_version_id", "name"})
)
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

    @PrePersist
    @PreUpdate
    public void trimName() {
        if (name != null) {
            name = name.trim();
        }
    }

}
