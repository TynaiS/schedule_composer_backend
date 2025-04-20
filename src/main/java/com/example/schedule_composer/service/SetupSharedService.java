package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.SetupSharedDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedDTOPost;
import com.example.schedule_composer.entity.SetupShared;

import java.util.List;

public interface SetupSharedService {

    SetupSharedDTOGet getById(Long id);

    SetupShared getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<SetupSharedDTOGet> getAll();

    List<SetupSharedDTOGet> getAllByGroupId(Long groupId);

    List<SetupShared> getAllEntities();

    SetupSharedDTOGet create(SetupSharedDTOPost createDto);

    SetupSharedDTOGet update(Long id, SetupSharedDTOPatch updateDto);

    void deleteById(Long id);


    //  ----  Shared groups ----


//    GroupDTOGet getGroupById(Long setupSharedId, Long groupId);
//
//    List<GroupDTOGet> getAllGroups(Long setupSharedId);
//
//    GroupDTOGet addGroup(Long setupSharedId, Long groupId);
//
//    void deleteGroupById(Long setupSharedId, Long groupId);
}
