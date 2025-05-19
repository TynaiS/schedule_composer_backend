package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.ScheduleLunchItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleLunchItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchItemDTOPost;
import com.example.schedule_composer.entity.ScheduleLunchItem;

import java.util.List;

public interface ScheduleLunchItemMapper {
    ScheduleLunchItemDTOGet fromEntityToGet(ScheduleLunchItem entity);
    List<ScheduleLunchItemDTOGet> fromEntityListToGetList(List<ScheduleLunchItem> entities);
    ScheduleLunchItem fromPostToEntity(ScheduleLunchItemDTOPost postDto);
    ScheduleLunchItem fromPatchToEntity(ScheduleLunchItemDTOPatch patchDto, ScheduleLunchItem scheduleLunchItemToUpdate);
}
