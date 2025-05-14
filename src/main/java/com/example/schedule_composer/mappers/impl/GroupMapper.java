package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.mappers.DTOMapper;
import com.example.schedule_composer.dto.patch.GroupDTOPatch;
import com.example.schedule_composer.dto.post.GroupDTOPost;
import com.example.schedule_composer.entity.Department;
import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.repository.GroupRepository;
import com.example.schedule_composer.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupMapper implements DTOMapper<GroupDTOGet, GroupDTOPost, GroupDTOPatch, Group, Long> {

    private final GroupRepository groupRepository;

    private final DepartmentMapper departmentMapper;

    private final DepartmentService departmentService;

    @Override
    public GroupDTOGet fromEntityToGet(Group group) {
        GroupDTOGet groupGet = new GroupDTOGet(group.getId(), group.getName(), departmentMapper.fromEntityToGet(group.getDepartment()), group.getSize());
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
        Department department = departmentService.getEntityById(groupDTOPost.getDepartmentId());

        Group group = Group.builder()
                .name(groupDTOPost.getName())
                .department(department)
                .size(groupDTOPost.getSize())
                .build();
        return group;
    }

    @Override
    public Group fromPatchToEntity(GroupDTOPatch groupDTOPatch, Long groupId) {

        Group existingGroup = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + groupId));

        if (groupDTOPatch.getName() != null){
            if(groupDTOPatch.getName().isBlank()){
                throw new IllegalArgumentException("Group name cannot be blank");
            }
            existingGroup.setName(groupDTOPatch.getName());
        }

        if (groupDTOPatch.getDepartmentId() != null){
            Department department = departmentService.getEntityById(groupDTOPatch.getDepartmentId());
            existingGroup.setDepartment(department);
        }

        if (groupDTOPatch.getSize() != null){
            existingGroup.setSize(groupDTOPatch.getSize());
        }


        return existingGroup;

    }
}
