package com.example.schedule_composer.repository;


import com.example.schedule_composer.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Override
    List<Schedule> findAll();

    boolean existsByIdAndOwnerEmail(Long id, String ownerEmail);

    Optional<Schedule> findByIdAndOwnerEmail(Long scheduleId, String ownerEmail);

    List<Schedule> findAllByOwnerId(Long ownerId);

    List<Schedule> findAllByEditors_Id(Long editorUserId);

}
