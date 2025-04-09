package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.CourseTeacherSharedDTOGet;
import com.example.schedule_composer.dto.mappers.CourseTeacherSharedMapper;
import com.example.schedule_composer.dto.patch.CourseTeacherSharedDTOPatch;
import com.example.schedule_composer.dto.post.CourseTeacherSharedDTOPost;
import com.example.schedule_composer.entity.CourseTeacherShared;
import com.example.schedule_composer.repository.CourseTeacherSharedRepository;
import com.example.schedule_composer.service.CourseTeacherSharedService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseTeacherSharedServiceImpl implements CourseTeacherSharedService {

    private final CourseTeacherSharedRepository courseTeacherSharedRepository;
    private final CourseTeacherSharedMapper courseTeacherSharedMapper;

    @Autowired
    public CourseTeacherSharedServiceImpl(CourseTeacherSharedRepository courseTeacherSharedRepository,CourseTeacherSharedMapper courseTeacherSharedMapper){
        this.courseTeacherSharedRepository = courseTeacherSharedRepository;
        this.courseTeacherSharedMapper = courseTeacherSharedMapper;
    }

    @Override
    public CourseTeacherSharedDTOGet getById(Long id) {
        return courseTeacherSharedMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public CourseTeacherShared getEntityById(Long id) {
        CourseTeacherShared entity = courseTeacherSharedRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course-Teacher-Shared not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!courseTeacherSharedRepository.existsById(id)) {
            throw new EntityNotFoundException("Course-Teacher-Shared not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<CourseTeacherSharedDTOGet> getAll() {
        List<CourseTeacherShared> entities = courseTeacherSharedRepository.findAll();

        return entities.stream()
                .map(courseTeacherSharedMapper::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public CourseTeacherSharedDTOGet create(CourseTeacherSharedDTOPost createDto) {
        CourseTeacherShared savedEntity = courseTeacherSharedRepository.save(courseTeacherSharedMapper.fromPostToEntity(createDto));
        return courseTeacherSharedMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public CourseTeacherSharedDTOGet update(Long id, CourseTeacherSharedDTOPatch updateDto) {
        CourseTeacherShared updatedEntity = courseTeacherSharedRepository.save(courseTeacherSharedMapper.fromPatchToEntity(updateDto, id));
        return courseTeacherSharedMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) courseTeacherSharedRepository.deleteById(id);
    }
}
