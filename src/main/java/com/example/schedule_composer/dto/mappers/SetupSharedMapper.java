package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.SetupSharedDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedDTOPost;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.entity.SetupShared;
import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.service.CourseService;
import com.example.schedule_composer.repository.SetupSharedRepository;
import com.example.schedule_composer.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SetupSharedMapper implements DTOMapper<SetupSharedDTOGet, SetupSharedDTOPost, SetupSharedDTOPatch, SetupShared, Long>{

    private final SetupSharedRepository scheduleSetupSharedRepository;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;

    @Autowired
    public SetupSharedMapper(SetupSharedRepository scheduleSetupSharedRepository, CourseService courseService, TeacherService teacherService, CourseMapper courseMapper, TeacherMapper teacherMapper) {
        this.scheduleSetupSharedRepository = scheduleSetupSharedRepository;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.courseMapper = courseMapper;
        this.teacherMapper = teacherMapper;
    }
    @Override
    public SetupSharedDTOGet fromEntityToGet(SetupShared scheduleSetupShared) {
        SetupSharedDTOGet scheduleSetupSharedGet = new SetupSharedDTOGet(
                scheduleSetupShared.getId(),
                scheduleSetupShared.getName(),
                courseMapper.fromEntityToGet(scheduleSetupShared.getCourse()),
                teacherMapper.fromEntityToGet(scheduleSetupShared.getTeacher()),
                scheduleSetupShared.getCoursePriority(),
                scheduleSetupShared.getHoursAWeek(),
                scheduleSetupShared.getHoursTotal(),
                scheduleSetupShared.getWeeksTotal(),
                scheduleSetupShared.getHoursInLab(),
                scheduleSetupShared.getPreferredRoomType());
        return scheduleSetupSharedGet;
    }

    @Override
    public List<SetupSharedDTOGet> fromEntityListToGetList(List<SetupShared> scheduleSetupShareds) {
        return scheduleSetupShareds.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public SetupShared fromPostToEntity(SetupSharedDTOPost scheduleSetupSharedDTOPost) {
        Course course = courseService.getEntityById(scheduleSetupSharedDTOPost.getCourseId());
        Teacher teacher = teacherService.getEntityById(scheduleSetupSharedDTOPost.getTeacherId());

        SetupShared scheduleSetupShared = SetupShared.builder()
                .name(scheduleSetupSharedDTOPost.getName())
                .course(course)
                .teacher(teacher)
                .coursePriority(scheduleSetupSharedDTOPost.getCoursePriority())
                .hoursAWeek(scheduleSetupSharedDTOPost.getHoursAWeek())
                .hoursTotal(scheduleSetupSharedDTOPost.getHoursTotal())
                .weeksTotal(scheduleSetupSharedDTOPost.getWeeksTotal())
                .hoursInLab(scheduleSetupSharedDTOPost.getHoursInLab())
                .preferredRoomType(scheduleSetupSharedDTOPost.getPreferredRoomType())
                .build();
        return scheduleSetupShared;
    }

    @Override
    public SetupShared fromPatchToEntity(SetupSharedDTOPatch scheduleSetupSharedDTOPatch, Long scheduleSetupSharedId) {

        SetupShared existingSetupShared = scheduleSetupSharedRepository.findById(scheduleSetupSharedId)
                .orElseThrow(() -> new EntityNotFoundException("Setup-Shared not found with id: " + scheduleSetupSharedId));


        if (scheduleSetupSharedDTOPatch.getName() != null){
            if(scheduleSetupSharedDTOPatch.getName().isBlank()){
                throw new IllegalArgumentException("Setup-Shared name cannot be blank");
            }
            existingSetupShared.setName(scheduleSetupSharedDTOPatch.getName());
        }

        if(scheduleSetupSharedDTOPatch.getCourseId() != null){
            Course course = courseService.getEntityById(scheduleSetupSharedDTOPatch.getCourseId());
            existingSetupShared.setCourse(course);
        }

        if(scheduleSetupSharedDTOPatch.getTeacherId() != null){
            Teacher teacher = teacherService.getEntityById(scheduleSetupSharedDTOPatch.getTeacherId());
            existingSetupShared.setTeacher(teacher);
        }

        if(scheduleSetupSharedDTOPatch.getCoursePriority() != null){
            existingSetupShared.setCoursePriority(scheduleSetupSharedDTOPatch.getCoursePriority());
        }

        if(scheduleSetupSharedDTOPatch.getHoursAWeek() != null){
            existingSetupShared.setHoursAWeek(scheduleSetupSharedDTOPatch.getHoursAWeek());
        }

        if(scheduleSetupSharedDTOPatch.getHoursTotal() != null){
            existingSetupShared.setHoursTotal(scheduleSetupSharedDTOPatch.getHoursTotal());
        }

        if(scheduleSetupSharedDTOPatch.getWeeksTotal() != null){
            existingSetupShared.setWeeksTotal(scheduleSetupSharedDTOPatch.getWeeksTotal());
        }

        if(scheduleSetupSharedDTOPatch.getHoursInLab() != null){
            existingSetupShared.setHoursInLab(scheduleSetupSharedDTOPatch.getHoursInLab());
        }

        if(scheduleSetupSharedDTOPatch.getPreferredRoomType() != null){
            existingSetupShared.setPreferredRoomType(scheduleSetupSharedDTOPatch.getPreferredRoomType());
        }

        return existingSetupShared;

    }
}
