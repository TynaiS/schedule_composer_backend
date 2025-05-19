package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.mappers.CourseMapper;
import com.example.schedule_composer.repository.CourseRepository;
import com.example.schedule_composer.service.CourseService;
import com.example.schedule_composer.service.ScheduleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {


    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final ScheduleService scheduleService;

    @Override
    public CourseDTOGet getById(Long id) {
        return courseMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public CourseDTOGet create(CourseDTOPost createDto) {
        Course savedEntity = courseRepository.save(courseMapper.fromPostToEntity(createDto));
        return courseMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public CourseDTOGet update(Long courseId, CourseDTOPatch updateDto) {
        Course existing = getEntityById(courseId);
        Course updatedEntity = courseRepository.save(courseMapper.fromPatchToEntity(updateDto, existing));
        return courseMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) courseRepository.deleteById(id);
    }

    @Override
    public List<CourseDTOGet> getAll() {
        List<Course> entities = courseRepository.findAll();

        return courseMapper.fromEntityListToGetList(entities);
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
    public List<Course> getAllEntities() {
        return courseRepository.findAll();
    }

    @Override
    public CourseDTOGet getByIdForUserSchedule(Long userId, Long scheduleId, Long courseId) {
        return courseMapper.fromEntityToGet(getEntityByIdForUserSchedule(userId, scheduleId, courseId));
    }

    @Override
    public List<CourseDTOGet> getAllForUserSchedule(Long userId, Long scheduleId) {
        return courseMapper.fromEntityListToGetList(getAllEntitiesForUserSchedule(userId, scheduleId));
    }

    @Override
    public CourseDTOGet createForUserSchedule(Long userId, Long scheduleId, CourseDTOPost request) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        Course course = courseMapper.fromPostToEntity(request);
        course.setSchedule(schedule);
        Course saved = courseRepository.save(course);
        return courseMapper.fromEntityToGet(saved);
    }

    @Override
    public CourseDTOGet updateForUserSchedule(Long userId, Long scheduleId, Long courseId, CourseDTOPatch patchRequest) {
        Course course = getEntityByIdForUserSchedule(userId, scheduleId, courseId);

        course = courseMapper.fromPatchToEntity(patchRequest, course);
        Course updated = courseRepository.save(course);
        return courseMapper.fromEntityToGet(updated);
    }

    @Override
    public void deleteByIdForUserSchedule(Long userId, Long scheduleId, Long courseId) {
        Course course = getEntityByIdForUserSchedule(userId, scheduleId, courseId);

        courseRepository.delete(course);
    }

    @Override
    public Course getEntityByIdForUserSchedule(Long userId, Long scheduleId, Long courseId) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        Course course = getEntityById(courseId);
        scheduleService.checkScheduleId(schedule, course.getSchedule().getId(), "Course");
        return course;
    }

    @Override
    public List<Course> getAllEntitiesForUserSchedule(Long userId, Long scheduleId) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        return courseRepository.findAllByScheduleId(schedule.getId());
    }

}
