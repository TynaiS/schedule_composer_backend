package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleLunchItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleLunchItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchItemDTOPost;
import com.example.schedule_composer.entity.ScheduleLunchItem;

import java.util.List;

public interface ScheduleLunchItemService {

    ScheduleLunchItemDTOGet getById(Long id);

    ScheduleLunchItem getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<ScheduleLunchItemDTOGet> getAll();

    List<ScheduleLunchItem> getAllEntities();

    ScheduleLunchItemDTOGet create(ScheduleLunchItemDTOPost createDto);

    ScheduleLunchItemDTOGet update(Long id, ScheduleLunchItemDTOPatch updateDto);

    void deleteById(Long id);
}
