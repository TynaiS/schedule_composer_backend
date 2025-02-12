package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.CourseGroupTeacherPatchRequest;
import com.example.schedule_composer.dto.CourseGroupTeacherPostRequest;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.entity.CourseGroupTeacher;
import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.repository.CourseGroupTeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseGroupTeacherService {

    private final CourseGroupTeacherRepository courseGroupTeacherRepository;
    private final GroupService groupService;
    private final CourseService courseService;
    private final TeacherService teacherService;

    @Autowired
    public CourseGroupTeacherService(
            CourseGroupTeacherRepository courseGroupTeacherRepository,
            GroupService groupService,
            CourseService courseService,
            TeacherService teacherService) {
        this.courseGroupTeacherRepository = courseGroupTeacherRepository;
        this.groupService = groupService;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    public List<CourseGroupTeacher> getCourseGroupTeachers() {
        return courseGroupTeacherRepository.findAll();
    }

    public CourseGroupTeacher getCourseGroupTeacherById(Long id) {
        return courseGroupTeacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CourseGroupTeacher not found with id " + id));
    }

    @Transactional
    public CourseGroupTeacher createCourseGroupTeacher(CourseGroupTeacherPostRequest request) {
        CourseGroupTeacher newCourseGroupTeacher = CourseGroupTeacher.builder()
                .group(groupService.getGroupById(request.getGroupId()))
                .course(courseService.getCourseById(request.getCourseId()))
                .teacher(teacherService.getTeacherById(request.getTeacherId()))
                .hoursAWeek(request.getHoursAWeek())
                .hoursTotal(request.getHoursTotal())
                .type(request.getType())
                .requiredRoomType(request.getRequiredRoomType())
                .build();
        CourseGroupTeacher savedCourseGroupTeacher = courseGroupTeacherRepository.save(newCourseGroupTeacher);
        return savedCourseGroupTeacher;
    }

    @Transactional
    public CourseGroupTeacher updateCourseGroupTeacher(Long id, CourseGroupTeacherPatchRequest patchRequest) {
        CourseGroupTeacher entity = courseGroupTeacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CourseGroupTeacher not found"));

        if (patchRequest.getGroupId() != null) {
            Group group = groupService.getGroupById(patchRequest.getGroupId());
            entity.setGroup(group);
        }

        if (patchRequest.getCourseId() != null) {
            Course course = courseService.getCourseById(patchRequest.getCourseId());
            entity.setCourse(course);
        }

        if (patchRequest.getTeacherId() != null) {
            Teacher teacher = teacherService.getTeacherById(patchRequest.getTeacherId());
            entity.setTeacher(teacher);
        }

        if (patchRequest.getHoursAWeek() != null) {
            entity.setHoursAWeek(patchRequest.getHoursAWeek());
        }

        if (patchRequest.getHoursTotal() != null) {
            entity.setHoursTotal(patchRequest.getHoursTotal());
        }

        if (patchRequest.getType() != null) {
            entity.setType(patchRequest.getType());
        }

        return courseGroupTeacherRepository.save(entity);
    }

    public void deleteCourseGroupTeacher(Long id) {
        courseGroupTeacherRepository.deleteById(id);
    }
}
