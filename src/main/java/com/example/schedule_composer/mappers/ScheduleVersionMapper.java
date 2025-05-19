package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.ScheduleVersionDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleVersionDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleVersionDTOPost;
import com.example.schedule_composer.entity.ScheduleVersion;

import java.util.List;

public interface  ScheduleVersionMapper {
    ScheduleVersionDTOGet fromEntityToGet(ScheduleVersion entity);
    List<ScheduleVersionDTOGet> fromEntityListToGetList(List<ScheduleVersion> entities);
    ScheduleVersion fromPostToEntity(ScheduleVersionDTOPost postDto);
    ScheduleVersion fromPatchToEntity(ScheduleVersionDTOPatch patchDto, ScheduleVersion scheduleVersionToUpdate);
}