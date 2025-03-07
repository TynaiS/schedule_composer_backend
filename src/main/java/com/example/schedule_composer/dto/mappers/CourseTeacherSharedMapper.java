package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.CourseTeacherSharedDTOGet;
import com.example.schedule_composer.dto.patch.CourseTeacherSharedDTOPatch;
import com.example.schedule_composer.dto.post.CourseTeacherSharedDTOPost;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.entity.CourseTeacherShared;
import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.service.CourseService;
import com.example.schedule_composer.repository.CourseTeacherSharedRepository;
import com.example.schedule_composer.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CourseTeacherSharedMapper implements DTOMapper<CourseTeacherSharedDTOGet, CourseTeacherSharedDTOPost, CourseTeacherSharedDTOPatch, CourseTeacherShared, Long>{

    private final CourseTeacherSharedRepository courseTeacherSharedRepository;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;

    @Autowired
    public CourseTeacherSharedMapper(CourseTeacherSharedRepository courseTeacherSharedRepository, CourseService courseService, TeacherService teacherService, CourseMapper courseMapper, TeacherMapper teacherMapper) {
        this.courseTeacherSharedRepository = courseTeacherSharedRepository;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.courseMapper = courseMapper;
        this.teacherMapper = teacherMapper;
    }
    @Override
    public CourseTeacherSharedDTOGet fromEntityToGet(CourseTeacherShared courseTeacherShared) {
        CourseTeacherSharedDTOGet courseTeacherSharedGet = new CourseTeacherSharedDTOGet(
                courseTeacherShared.getId(),
                courseTeacherShared.getName(),
                courseMapper.fromEntityToGet(courseTeacherShared.getCourse()),
                teacherMapper.fromEntityToGet(courseTeacherShared.getTeacher()),
                courseTeacherShared.getCoursePriority(),
                courseTeacherShared.getHoursAWeek(),
                courseTeacherShared.getHoursTotal(),
                courseTeacherShared.getWeeksTotal(),
                courseTeacherShared.getHoursInLab(),
                courseTeacherShared.getPreferredRoomType());
        return courseTeacherSharedGet;
    }

    @Override
    public CourseTeacherShared fromPostToEntity(CourseTeacherSharedDTOPost courseTeacherSharedDTOPost) {
        Course course = courseService.getEntityById(courseTeacherSharedDTOPost.getCourseId());
        Teacher teacher = teacherService.getEntityById(courseTeacherSharedDTOPost.getTeacherId());

        CourseTeacherShared courseTeacherShared = CourseTeacherShared.builder()
                .name(courseTeacherSharedDTOPost.getName())
                .course(course)
                .teacher(teacher)
                .coursePriority(courseTeacherSharedDTOPost.getCoursePriority())
                .hoursAWeek(courseTeacherSharedDTOPost.getHoursAWeek())
                .hoursTotal(courseTeacherSharedDTOPost.getHoursTotal())
                .weeksTotal(courseTeacherSharedDTOPost.getWeeksTotal())
                .hoursInLab(courseTeacherSharedDTOPost.getHoursInLab())
                .preferredRoomType(courseTeacherSharedDTOPost.getPreferredRoomType())
                .build();
        return courseTeacherShared;
    }

    @Override
    public CourseTeacherShared fromPatchToEntity(CourseTeacherSharedDTOPatch courseTeacherSharedDTOPatch, Long courseTeacherSharedId) {

        CourseTeacherShared existingCourseTeacherShared = courseTeacherSharedRepository.findById(courseTeacherSharedId)
                .orElseThrow(() -> new EntityNotFoundException("Course-Teacher-Shared not found with id: " + courseTeacherSharedId));


        if (courseTeacherSharedDTOPatch.getName() != null){
            if(courseTeacherSharedDTOPatch.getName().isBlank()){
                throw new IllegalArgumentException("Course-Teacher-Shared name cannot be blank");
            }
            existingCourseTeacherShared.setName(courseTeacherSharedDTOPatch.getName());
        }

        if(courseTeacherSharedDTOPatch.getCourseId() != null){
            Course course = courseService.getEntityById(courseTeacherSharedDTOPatch.getCourseId());
            existingCourseTeacherShared.setCourse(course);
        }

        if(courseTeacherSharedDTOPatch.getTeacherId() != null){
            Teacher teacher = teacherService.getEntityById(courseTeacherSharedDTOPatch.getTeacherId());
            existingCourseTeacherShared.setTeacher(teacher);
        }

        if(courseTeacherSharedDTOPatch.getCoursePriority() != null){
            existingCourseTeacherShared.setCoursePriority(courseTeacherSharedDTOPatch.getCoursePriority());
        }

        if(courseTeacherSharedDTOPatch.getHoursAWeek() != null){
            existingCourseTeacherShared.setHoursAWeek(courseTeacherSharedDTOPatch.getHoursAWeek());
        }

        if(courseTeacherSharedDTOPatch.getHoursTotal() != null){
            existingCourseTeacherShared.setHoursTotal(courseTeacherSharedDTOPatch.getHoursTotal());
        }

        if(courseTeacherSharedDTOPatch.getWeeksTotal() != null){
            existingCourseTeacherShared.setWeeksTotal(courseTeacherSharedDTOPatch.getWeeksTotal());
        }

        if(courseTeacherSharedDTOPatch.getHoursInLab() != null){
            existingCourseTeacherShared.setHoursInLab(courseTeacherSharedDTOPatch.getHoursInLab());
        }

        if(courseTeacherSharedDTOPatch.getPreferredRoomType() != null){
            existingCourseTeacherShared.setPreferredRoomType(courseTeacherSharedDTOPatch.getPreferredRoomType());
        }

        return existingCourseTeacherShared;

    }
}
