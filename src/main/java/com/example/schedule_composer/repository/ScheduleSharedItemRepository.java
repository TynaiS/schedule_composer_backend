package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.ScheduleSharedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleSharedItemRepository extends JpaRepository<ScheduleSharedItem, Long> {
    @Override
    List<ScheduleSharedItem> findAll();

}
