package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.SetupItemDTOGet;
import com.example.schedule_composer.dto.patch.SetupItemDTOPatch;
import com.example.schedule_composer.dto.post.SetupItemDTOPost;
import com.example.schedule_composer.entity.SetupItem;

import java.util.List;

public interface SetupItemMapper {
    SetupItemDTOGet fromEntityToGet(SetupItem entity);
    List<SetupItemDTOGet> fromEntityListToGetList(List<SetupItem> entities);
    SetupItem fromPostToEntity(SetupItemDTOPost postDto);
    SetupItem fromPatchToEntity(SetupItemDTOPatch patchDto, SetupItem setupItemToUpdate);
}
