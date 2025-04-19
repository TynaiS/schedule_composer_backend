package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleSharedDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleSharedDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedDTOPost;
import com.example.schedule_composer.entity.ScheduleShared;

import java.util.List;

public interface ScheduleSharedService {

    ScheduleSharedDTOGet getById(Long id);

    ScheduleShared getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<ScheduleSharedDTOGet> getAll();

    List<ScheduleShared> getAllEntities();

    ScheduleSharedDTOGet create(ScheduleSharedDTOPost createDto);

    ScheduleSharedDTOGet create(ScheduleShared createEntity);

    ScheduleSharedDTOGet update(Long id, ScheduleSharedDTOPatch updateDto);

    void deleteById(Long id);
}
