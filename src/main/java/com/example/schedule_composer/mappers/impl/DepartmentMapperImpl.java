package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.DepartmentDTOGet;
import com.example.schedule_composer.dto.patch.DepartmentDTOPatch;
import com.example.schedule_composer.dto.post.DepartmentDTOPost;
import com.example.schedule_composer.entity.Department;
import com.example.schedule_composer.mappers.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentDTOGet fromEntityToGet(Department department) {
        DepartmentDTOGet departmentGet = DepartmentDTOGet.builder()
                .id(department.getId())
                .scheduleId(department.getSchedule().getId())
                .name(department.getName())
                .build();
        return departmentGet;
    }

    @Override
    public List<DepartmentDTOGet> fromEntityListToGetList(List<Department> departments) {
        return departments.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public Department fromPostToEntity(DepartmentDTOPost departmentDTOPost) {
        Department department = Department.builder()
                .name(departmentDTOPost.getName())
                .build();
        return department;
    }

    @Override
    public Department fromPatchToEntity(DepartmentDTOPatch departmentDTOPatch, Department departmentToUpdate) {

        if (departmentDTOPatch.getName() != null){
            if(departmentDTOPatch.getName().isBlank()){
                throw new IllegalArgumentException("Department name cannot be blank");
            }
            departmentToUpdate.setName(departmentDTOPatch.getName());
        }
        return departmentToUpdate;

    }
}
