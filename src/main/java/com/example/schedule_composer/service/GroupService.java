package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.dto.patch.GroupDTOPatch;
import com.example.schedule_composer.dto.post.GroupDTOPost;
import com.example.schedule_composer.entity.Group;


import java.util.List;

public interface GroupService {

    GroupDTOGet getById(Long id);

    Group getEntityById(Long id);

    Boolean checkIfExists(Long id);

    Group checkIfExistsAndGetEntity(Long id);

    List<Group> checkIfAllExistAndGetEntities(List<Long> groupIds);

    List<GroupDTOGet> getAll();

    List<Group> getAllEntities();

    GroupDTOGet create(GroupDTOPost createDto);

    GroupDTOGet update(Long id, GroupDTOPatch updateDto);

    void deleteById(Long id);
}
