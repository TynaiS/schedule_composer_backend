package com.example.schedule_composer.repository;


import com.example.schedule_composer.entity.ScheduleVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleVersionRepository extends JpaRepository<ScheduleVersion, Long> {
    @Override
    List<ScheduleVersion> findAll();

    List<ScheduleVersion> findAllByScheduleId(Long scheduleId);

}
