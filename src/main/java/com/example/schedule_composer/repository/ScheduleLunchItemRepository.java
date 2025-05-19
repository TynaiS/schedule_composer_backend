package com.example.schedule_composer.repository;


import com.example.schedule_composer.entity.ScheduleLunchItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleLunchItemRepository extends JpaRepository<ScheduleLunchItem, Long> {
    @Override
    List<ScheduleLunchItem> findAll();

    List<ScheduleLunchItem> findAllByScheduleVersionId(Long scheduleVersionId);

}
