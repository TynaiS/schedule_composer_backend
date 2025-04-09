package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleDTOPost;
import com.example.schedule_composer.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    ScheduleDTOGet getById(Long id);

    Schedule getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<ScheduleDTOGet> getAll();

    ScheduleDTOGet create(ScheduleDTOPost createDto);

    ScheduleDTOGet update(Long id, ScheduleDTOPatch updateDto);

    void deleteById(Long id);
}
