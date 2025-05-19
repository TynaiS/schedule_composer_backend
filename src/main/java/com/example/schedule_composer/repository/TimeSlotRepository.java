package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    @Override
    List<TimeSlot> findAll();

    List<TimeSlot> findAllByScheduleId(Long scheduleId);

}


