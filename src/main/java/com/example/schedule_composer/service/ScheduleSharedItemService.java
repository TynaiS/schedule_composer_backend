package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleSharedItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedItemDTOPost;
import com.example.schedule_composer.entity.ScheduleSharedItem;

import java.util.List;

public interface ScheduleSharedItemService {

    ScheduleSharedItemDTOGet getById(Long id);

    ScheduleSharedItem getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<ScheduleSharedItemDTOGet> getAll();

    List<ScheduleSharedItem> getAllEntities();

    ScheduleSharedItemDTOGet create(ScheduleSharedItemDTOPost createDto);

    ScheduleSharedItemDTOGet create(ScheduleSharedItem createEntity);

    ScheduleSharedItemDTOGet update(Long id, ScheduleSharedItemDTOPatch updateDto);

    void deleteById(Long id);
}
