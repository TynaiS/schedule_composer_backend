package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.DepartmentDTOGet;
import com.example.schedule_composer.dto.mappers.impl.DepartmentMapper;
import com.example.schedule_composer.dto.patch.DepartmentDTOPatch;
import com.example.schedule_composer.dto.post.DepartmentDTOPost;
import com.example.schedule_composer.entity.Department;
import com.example.schedule_composer.repository.DepartmentRepository;
import com.example.schedule_composer.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository groupRepository;
    private final DepartmentMapper groupMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository groupRepository,DepartmentMapper groupMapper){
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public DepartmentDTOGet getById(Long id) {
        return groupMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public Department getEntityById(Long id) {
        Department entity = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new EntityNotFoundException("Department not found with id: " + id);
        }
        return true;
    }

    @Override
    public Department checkIfExistsAndGetEntity(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new EntityNotFoundException("Department not found with id: " + id);
        }
        return getEntityById(id);
    }

    @Override
    public List<Department> checkIfAllExistAndGetEntities(List<Long> groupIds) {
        return groupIds.stream()
                .map(this::checkIfExistsAndGetEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<DepartmentDTOGet> getAll() {
        List<Department> entities = groupRepository.findAll();

        return groupMapper.fromEntityListToGetList(entities);
    }

    @Override
    public List<Department> getAllEntities() {
        return groupRepository.findAll();
    }

    @Override
    public DepartmentDTOGet create(DepartmentDTOPost createDto) {
        Department savedEntity = groupRepository.save(groupMapper.fromPostToEntity(createDto));
        return groupMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public DepartmentDTOGet update(Long id, DepartmentDTOPatch updateDto) {
        Department updatedEntity = groupRepository.save(groupMapper.fromPatchToEntity(updateDto, id));
        return groupMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) groupRepository.deleteById(id);
    }
}
