package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.TimeSlotDTOPatch;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.utils.TimeSlotOrdered;

import java.util.List;

public interface TimeSlotMapper {
        TimeSlotDTOGet fromEntityToGet(TimeSlot entity);
        List<TimeSlotDTOGet> fromEntityListToGetList(List<TimeSlot> entities);
        TimeSlot fromPostToEntity(TimeSlotDTOPost postDto);
        TimeSlot fromPatchToEntity(TimeSlotDTOPatch patchDto, Long id);
        List<TimeSlotOrdered> fromEntityListToOrderedList(List<TimeSlot> timeSlots);
        List<TimeSlot> fromOrderedListToEntityList(List<TimeSlotOrdered> timeSlotsOrdered);
}
