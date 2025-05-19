package com.example.schedule_composer.repository;


import com.example.schedule_composer.entity.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleItemRepository extends JpaRepository<ScheduleItem, Long> {
    @Override
    List<ScheduleItem> findAll();

    List<ScheduleItem> findAllByScheduleVersionId(Long scheduleVersionId);
}
