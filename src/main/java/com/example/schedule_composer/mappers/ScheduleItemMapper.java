package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.ScheduleItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleItemDTOPost;
import com.example.schedule_composer.entity.ScheduleItem;

import java.util.List;

public interface ScheduleItemMapper {
    ScheduleItemDTOGet fromEntityToGet(ScheduleItem entity);
    List<ScheduleItemDTOGet> fromEntityListToGetList(List<ScheduleItem> entities);
    ScheduleItem fromPostToEntity(ScheduleItemDTOPost postDto);
    ScheduleItem fromPatchToEntity(ScheduleItemDTOPatch patchDto, ScheduleItem scheduleItemToUpdate);
}
