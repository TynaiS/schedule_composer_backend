package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.SetupSharedNameDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedNameDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedNameDTOPost;
import com.example.schedule_composer.entity.SetupSharedName;


import java.util.List;

public interface SetupSharedNameService {

    SetupSharedNameDTOGet getById(Long id);

    SetupSharedName getEntityById(Long id);

    Boolean checkIfExists(Long id);

    SetupSharedName checkIfExistsAndGetEntity(Long id);

    List<SetupSharedName> checkIfAllExistAndGetEntities(List<Long> groupIds);

    List<SetupSharedNameDTOGet> getAll();

    List<SetupSharedName> getAllEntities();

    SetupSharedNameDTOGet create(SetupSharedNameDTOPost createDto);

    SetupSharedNameDTOGet update(Long id, SetupSharedNameDTOPatch updateDto);

    void deleteById(Long id);
}
