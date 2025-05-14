package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.mappers.impl.CourseMapper;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.repository.CourseRepository;
import com.example.schedule_composer.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {


    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper){
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseDTOGet getById(Long id) {
        return courseMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public Course getEntityById(Long id){
        Course entity = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id:" + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("Course not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<CourseDTOGet> getAll() {
        List<Course> entities = courseRepository.findAll();

        return courseMapper.fromEntityListToGetList(entities);
    }

    @Override
    public List<Course> getAllEntities() {
        return courseRepository.findAll();
    }

    @Override
    public CourseDTOGet create(CourseDTOPost createDto) {
        Course savedEntity = courseRepository.save(courseMapper.fromPostToEntity(createDto));
        return courseMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public CourseDTOGet update(Long id, CourseDTOPatch updateDto) {
        Course updatedEntity = courseRepository.save(courseMapper.fromPatchToEntity(updateDto, id));
        return courseMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) courseRepository.deleteById(id);
    }
}
