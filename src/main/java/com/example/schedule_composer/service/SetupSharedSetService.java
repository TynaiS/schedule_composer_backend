package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.SetupSharedSetDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedSetDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedSetDTOPost;
import com.example.schedule_composer.entity.SetupSharedSet;


import java.util.List;

public interface SetupSharedSetService {

    SetupSharedSetDTOGet getById(Long id);

    SetupSharedSet getEntityById(Long id);

    Boolean checkIfExists(Long id);

    SetupSharedSet checkIfExistsAndGetEntity(Long id);

    List<SetupSharedSet> checkIfAllExistAndGetEntities(List<Long> groupIds);

    List<SetupSharedSetDTOGet> getAll();

    List<SetupSharedSet> getAllEntities();

    SetupSharedSetDTOGet create(SetupSharedSetDTOPost createDto);

    SetupSharedSetDTOGet update(Long id, SetupSharedSetDTOPatch updateDto);

    void deleteById(Long id);
}
