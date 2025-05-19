package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.SetupItem;
import com.example.schedule_composer.entity.SetupSharedSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetupSharedSetRepository extends JpaRepository<SetupSharedSet, Long> {
    @Override
    List<SetupSharedSet> findAll();

    List<SetupSharedSet> findAllByScheduleVersionId(Long scheduleVersionId);

}
