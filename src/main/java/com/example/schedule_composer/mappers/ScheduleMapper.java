package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.ScheduleDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleDTOPost;
import com.example.schedule_composer.entity.Schedule;

import java.util.List;

public interface  ScheduleMapper {

    ScheduleDTOGet fromEntityToGet(Schedule entity);
    List<ScheduleDTOGet> fromEntityListToGetList(List<Schedule> entities);
    Schedule fromPostToEntity(ScheduleDTOPost postDto);
    Schedule fromPatchToEntity(ScheduleDTOPatch patchDto, Schedule scheduleToUpdate);
}