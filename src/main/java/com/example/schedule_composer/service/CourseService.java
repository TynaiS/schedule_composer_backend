package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.mappers.CourseMapper;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService implements CrudService<CourseDTOGet, CourseDTOPost, CourseDTOPatch, Course,Long>{


    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper){
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

        return entities.stream()
                .map(courseMapper::fromEntityToGet)
                .collect(Collectors.toList());
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
