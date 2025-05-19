package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.dto.patch.GroupDTOPatch;
import com.example.schedule_composer.dto.post.GroupDTOPost;
import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.mappers.DepartmentMapper;
import com.example.schedule_composer.mappers.GroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupMapperImpl implements GroupMapper {

    private final DepartmentMapper departmentMapper;

    @Override
    public GroupDTOGet fromEntityToGet(Group group) {
        GroupDTOGet groupGet = GroupDTOGet.builder()
                .id(group.getId())
                .scheduleId(group.getSchedule().getId())
                .name(group.getName())
                .department(departmentMapper.fromEntityToGet(group.getDepartment()))
                .size(group.getSize())
                .build();
        return groupGet;
    }

    @Override
    public List<GroupDTOGet> fromEntityListToGetList(List<Group> groups) {
        return groups.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public Group fromPostToEntity(GroupDTOPost groupDTOPost) {

        Group group = Group.builder()
                .name(groupDTOPost.getName())
                .size(groupDTOPost.getSize())
                .build();
        return group;
    }

    @Override
    public Group fromPatchToEntity(GroupDTOPatch groupDTOPatch, Group groupToUpdate) {

        if (groupDTOPatch.getName() != null){
            if(groupDTOPatch.getName().isBlank()){
                throw new IllegalArgumentException("Group name cannot be blank");
            }
            groupToUpdate.setName(groupDTOPatch.getName());
        }

        if (groupDTOPatch.getSize() != null){
            groupToUpdate.setSize(groupDTOPatch.getSize());
        }


        return groupToUpdate;

    }
}
