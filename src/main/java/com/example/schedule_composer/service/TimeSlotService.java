package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.TimeSlotDTOPatch;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.entity.TimeSlot;

import java.util.List;

public interface TimeSlotService {

    TimeSlotDTOGet getById(Long id);

    TimeSlot getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<TimeSlotDTOGet> getAll();

    TimeSlotDTOGet create(TimeSlotDTOPost createDto);

    TimeSlotDTOGet update(Long id, TimeSlotDTOPatch updateDto);

    void deleteById(Long id);
}
