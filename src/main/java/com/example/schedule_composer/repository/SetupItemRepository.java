package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.SetupItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetupItemRepository extends JpaRepository<SetupItem, Long> {
    @Override
    List<SetupItem> findAll();

    List<SetupItem> findAllByGroupId(Long groupId);

    List<SetupItem> findAllByGroupIdAndScheduleVersionId(Long groupId, Long scheduleVersionId);

    List<SetupItem> findAllByScheduleVersionId(Long scheduleVersionId);
}
