package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.SetupSharedItemDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedItemDTOPost;
import com.example.schedule_composer.entity.SetupSharedItem;

import java.util.List;

public interface SetupSharedItemMapper {
    SetupSharedItemDTOGet fromEntityToGet(SetupSharedItem entity);
    List<SetupSharedItemDTOGet> fromEntityListToGetList(List<SetupSharedItem> entities);
    SetupSharedItem fromPostToEntity(SetupSharedItemDTOPost postDto);
    SetupSharedItem fromPatchToEntity(SetupSharedItemDTOPatch patchDto, SetupSharedItem setupSharedItemToUpdate);
}
