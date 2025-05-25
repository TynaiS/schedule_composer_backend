package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.SetupSharedSetDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedSetDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedSetDTOPost;
import com.example.schedule_composer.entity.ScheduleVersion;
import com.example.schedule_composer.entity.SetupSharedSet;


import java.util.List;

public interface SetupSharedSetService {

    SetupSharedSetDTOGet getById(Long id);
    SetupSharedSetDTOGet create(SetupSharedSetDTOPost createDto);
    SetupSharedSetDTOGet update(Long id, SetupSharedSetDTOPatch updateDto);
    void deleteById(Long id);
    List<SetupSharedSetDTOGet> getAll();


    SetupSharedSet getEntityById(Long id);
    Boolean checkIfExists(Long id);
    List<SetupSharedSet> checkIfAllExistAndGetEntities(List<Long> groupIds);
    List<SetupSharedSet> getAllEntities();



    SetupSharedSetDTOGet getByIdForUserScheduleVersion(Long userId, Long setupSharedSetId);
    List<SetupSharedSetDTOGet> getAllForUserScheduleVersion(Long userId, Long scheduleVersionId);
    SetupSharedSetDTOGet createForUserScheduleVersion(Long userId, Long scheduleVersionId, SetupSharedSetDTOPost request);
    SetupSharedSetDTOGet updateForUserScheduleVersion(Long userId, Long setupSharedSetId, SetupSharedSetDTOPatch patchRequest);
    void deleteByIdForUserScheduleVersion(Long userId, Long setupSharedSetId);
    void checkSetupSharedSetId(SetupSharedSet setupSharedSet, Long setupSharedSetId, String entityName);


    SetupSharedSet getEntityByIdForUserScheduleVersion(Long userId, Long setupSharedSetId);
    List<SetupSharedSet> getAllEntitiesForUserScheduleVersion(Long userId, Long scheduleVersionId);

    void checkUserAccessToScheduleSharedSet(SetupSharedSet setupSharedSet, Long userId);
}
