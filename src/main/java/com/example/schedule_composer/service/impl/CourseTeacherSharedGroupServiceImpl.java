package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.CourseTeacherSharedGroupDTOGet;
import com.example.schedule_composer.dto.mappers.CourseTeacherSharedGroupMapper;
import com.example.schedule_composer.dto.patch.CourseTeacherSharedGroupDTOPatch;
import com.example.schedule_composer.dto.post.CourseTeacherSharedGroupDTOPost;
import com.example.schedule_composer.entity.CourseTeacherSharedGroup;
import com.example.schedule_composer.repository.CourseTeacherSharedGroupRepository;
import com.example.schedule_composer.service.CourseTeacherSharedGroupService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseTeacherSharedGroupServiceImpl implements CourseTeacherSharedGroupService {

    private final CourseTeacherSharedGroupRepository courseTeacherSharedGroupRepository;
    private final CourseTeacherSharedGroupMapper courseTeacherSharedGroupMapper;

    @Autowired
    public CourseTeacherSharedGroupServiceImpl(CourseTeacherSharedGroupRepository courseTeacherSharedGroupRepository,CourseTeacherSharedGroupMapper courseTeacherSharedGroupMapper){
        this.courseTeacherSharedGroupRepository = courseTeacherSharedGroupRepository;
        this.courseTeacherSharedGroupMapper = courseTeacherSharedGroupMapper;
    }

    @Override
    public CourseTeacherSharedGroupDTOGet getById(Long id) {
        return courseTeacherSharedGroupMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public CourseTeacherSharedGroup getEntityById(Long id) {
        CourseTeacherSharedGroup entity = courseTeacherSharedGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course-Teacher-Shared-Group not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!courseTeacherSharedGroupRepository.existsById(id)) {
            throw new EntityNotFoundException("Course-Teacher-Shared-Group not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<CourseTeacherSharedGroupDTOGet> getAll() {
        List<CourseTeacherSharedGroup> entities = courseTeacherSharedGroupRepository.findAll();

        return courseTeacherSharedGroupMapper.fromEntityListToGetList(entities);
    }

    @Override
    public CourseTeacherSharedGroupDTOGet create(CourseTeacherSharedGroupDTOPost createDto) {
        CourseTeacherSharedGroup savedEntity = courseTeacherSharedGroupRepository.save(courseTeacherSharedGroupMapper.fromPostToEntity(createDto));
        return courseTeacherSharedGroupMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public CourseTeacherSharedGroupDTOGet update(Long id, CourseTeacherSharedGroupDTOPatch updateDto) {
        CourseTeacherSharedGroup updatedEntity = courseTeacherSharedGroupRepository.save(courseTeacherSharedGroupMapper.fromPatchToEntity(updateDto, id));
        return courseTeacherSharedGroupMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) courseTeacherSharedGroupRepository.deleteById(id);
    }
}
