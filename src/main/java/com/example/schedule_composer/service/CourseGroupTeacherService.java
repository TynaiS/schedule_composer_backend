package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.CourseGroupTeacherPostRequest;
import com.example.schedule_composer.entity.CourseGroupTeacher;
import com.example.schedule_composer.repository.CourseGroupTeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CourseGroupTeacher createCourseGroupTeacher(CourseGroupTeacherPostRequest request) {
        CourseGroupTeacher newCourseGroupTeacher = CourseGroupTeacher.builder()
                .id(request.getId())
                .group(groupService.getGroupById(request.getGroupId()))
                .course(courseService.getCourseById(request.getCourseId()))
                .teacher(teacherService.getTeacherById(request.getTeacherId()))
                .hoursAWeek(request.getHoursAWeek())
                .hoursTotal(request.getHoursTotal())
                .type(request.getType())
                .build();
        CourseGroupTeacher savedCourseGroupTeacher = courseGroupTeacherRepository.save(newCourseGroupTeacher);
        return savedCourseGroupTeacher;
    }

    public void deleteCourseGroupTeacher(Long id) {
        courseGroupTeacherRepository.deleteById(id);
    }
}
