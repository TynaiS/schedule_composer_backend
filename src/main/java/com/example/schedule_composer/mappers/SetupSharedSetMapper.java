package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.SetupSharedSetDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedSetDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedSetDTOPost;
import com.example.schedule_composer.entity.SetupSharedSet;

import java.util.List;

public interface SetupSharedSetMapper {
    SetupSharedSetDTOGet fromEntityToGet(SetupSharedSet entity);
    List<SetupSharedSetDTOGet> fromEntityListToGetList(List<SetupSharedSet> entities);
    SetupSharedSet fromPostToEntity(SetupSharedSetDTOPost postDto);
    SetupSharedSet fromPatchToEntity(SetupSharedSetDTOPatch patchDto, SetupSharedSet setupSharedSetToUpdate);
}
