package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.ScheduleSharedItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedItemDTOPost;
import com.example.schedule_composer.entity.ScheduleSharedItem;

import java.util.List;

public interface ScheduleSharedItemMapper {
    ScheduleSharedItemDTOGet fromEntityToGet(ScheduleSharedItem entity);
    List<ScheduleSharedItemDTOGet> fromEntityListToGetList(List<ScheduleSharedItem> entities);
    ScheduleSharedItem fromPostToEntity(ScheduleSharedItemDTOPost postDto);
    ScheduleSharedItem fromPatchToEntity(ScheduleSharedItemDTOPatch patchDto, ScheduleSharedItem scheduleSharedItemToUpdate);
}
