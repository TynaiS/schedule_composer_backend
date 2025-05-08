package com.example.schedule_composer.dto.mappers.impl;

import com.example.schedule_composer.dto.get.DepartmentDTOGet;
import com.example.schedule_composer.dto.mappers.DTOMapper;
import com.example.schedule_composer.dto.patch.DepartmentDTOPatch;
import com.example.schedule_composer.dto.post.DepartmentDTOPost;
import com.example.schedule_composer.entity.Department;
import com.example.schedule_composer.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentMapper implements DTOMapper<DepartmentDTOGet, DepartmentDTOPost, DepartmentDTOPatch, Department, Long> {

    private final DepartmentRepository groupRepository;

    @Override
    public DepartmentDTOGet fromEntityToGet(Department group) {
        DepartmentDTOGet groupGet = new DepartmentDTOGet(group.getId(), group.getName());
        return groupGet;
    }

    @Override
    public List<DepartmentDTOGet> fromEntityListToGetList(List<Department> groups) {
        return groups.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public Department fromPostToEntity(DepartmentDTOPost groupDTOPost) {
        Department group = Department.builder()
                .name(groupDTOPost.getName())
                .build();
        return group;
    }

    @Override
    public Department fromPatchToEntity(DepartmentDTOPatch groupDTOPatch, Long groupId) {

        Department existingDepartment = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + groupId));

        if (groupDTOPatch.getName() != null){
            if(groupDTOPatch.getName().isBlank()){
                throw new IllegalArgumentException("Department name cannot be blank");
            }
            existingDepartment.setName(groupDTOPatch.getName());
        }
        return existingDepartment;

    }
}
