package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.TimeSlotDTOPatch;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.entity.TimeSlot;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public interface TimeSlotService {

    TimeSlotDTOGet getById(Long id);

    TimeSlot getEntityById(Long id);

    Boolean checkIfExists(Long id);

    TimeSlot checkIfExistsAndGetEntity(Long id);

    List<TimeSlot> checkIfAllExistAndGetEntities(List<Long> timeSlotIds);

    List<TimeSlotDTOGet> getAll();

    TimeSlotDTOGet create(TimeSlotDTOPost createDto);

    TimeSlotDTOGet update(Long id, TimeSlotDTOPatch updateDto);

    void deleteById(Long id);
}
