package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleItemDTOPost;
import com.example.schedule_composer.entity.ScheduleItem;

import java.util.List;

public interface ScheduleItemService {

    ScheduleItemDTOGet getById(Long id);

    ScheduleItem getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<ScheduleItemDTOGet> getAll();

    List<ScheduleItem> getAllEntities();

    ScheduleItemDTOGet create(ScheduleItemDTOPost createDto);

    ScheduleItemDTOGet create(ScheduleItem createEntity);

    ScheduleItemDTOGet update(Long id, ScheduleItemDTOPatch updateDto);

    void deleteById(Long id);
}
