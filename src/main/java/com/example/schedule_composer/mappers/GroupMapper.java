package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.dto.patch.GroupDTOPatch;
import com.example.schedule_composer.dto.post.GroupDTOPost;
import com.example.schedule_composer.entity.Group;

import java.util.List;

public interface GroupMapper {
    GroupDTOGet fromEntityToGet(Group group);
    List<GroupDTOGet> fromEntityListToGetList(List<Group> groups);
    Group fromPostToEntity(GroupDTOPost groupDTOPost);
    Group fromPatchToEntity(GroupDTOPatch groupDTOPatch, Group groupToUpdate);
}
