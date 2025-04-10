package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.SetupDTOGet;
import com.example.schedule_composer.dto.patch.SetupDTOPatch;
import com.example.schedule_composer.dto.post.SetupDTOPost;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.entity.Setup;
import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.repository.SetupRepository;
import com.example.schedule_composer.service.CourseService;
import com.example.schedule_composer.service.GroupService;
import com.example.schedule_composer.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SetupMapper implements DTOMapper<SetupDTOGet, SetupDTOPost, SetupDTOPatch, Setup, Long>{

    private final SetupRepository scheduleSetupRegularRepository;
    private final GroupService groupService;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final GroupMapper groupMapper;
    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;

    @Autowired
    public SetupMapper(SetupRepository scheduleSetupRegularRepository, GroupService groupService, CourseService courseService, TeacherService teacherService, GroupMapper groupMapper, CourseMapper courseMapper, TeacherMapper teacherMapper) {
        this.scheduleSetupRegularRepository = scheduleSetupRegularRepository;
        this.groupService = groupService;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.groupMapper = groupMapper;
        this.courseMapper = courseMapper;
        this.teacherMapper = teacherMapper;
    }
    @Override
    public SetupDTOGet fromEntityToGet(Setup scheduleSetupRegular) {
        SetupDTOGet scheduleSetupRegularGet = new SetupDTOGet(
                scheduleSetupRegular.getId(),
                groupMapper.fromEntityToGet(scheduleSetupRegular.getGroup()),
                courseMapper.fromEntityToGet(scheduleSetupRegular.getCourse()),
                teacherMapper.fromEntityToGet(scheduleSetupRegular.getTeacher()),
                scheduleSetupRegular.getCoursePriority(),
                scheduleSetupRegular.getHoursAWeek(),
                scheduleSetupRegular.getHoursTotal(),
                scheduleSetupRegular.getWeeksTotal(),
                scheduleSetupRegular.getHoursInLab(),
                scheduleSetupRegular.getPreferredRoomType());
        return scheduleSetupRegularGet;
    }

    @Override
    public List<SetupDTOGet> fromEntityListToGetList(List<Setup> scheduleSetupRegulars) {
        return scheduleSetupRegulars.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public Setup fromPostToEntity(SetupDTOPost scheduleSetupRegularDTOPost) {
        Group group = groupService.getEntityById(scheduleSetupRegularDTOPost.getGroupId());
        Course course = courseService.getEntityById(scheduleSetupRegularDTOPost.getCourseId());
        Teacher teacher = teacherService.getEntityById(scheduleSetupRegularDTOPost.getTeacherId());

        Setup scheduleSetupRegular = Setup.builder()
                .group(group)
                .course(course)
                .teacher(teacher)
                .coursePriority(scheduleSetupRegularDTOPost.getCoursePriority())
                .hoursAWeek(scheduleSetupRegularDTOPost.getHoursAWeek())
                .hoursTotal(scheduleSetupRegularDTOPost.getHoursTotal())
                .weeksTotal(scheduleSetupRegularDTOPost.getWeeksTotal())
                .hoursInLab(scheduleSetupRegularDTOPost.getHoursInLab())
                .preferredRoomType(scheduleSetupRegularDTOPost.getPreferredRoomType())
                .build();
        return scheduleSetupRegular;
    }

    @Override
    public Setup fromPatchToEntity(SetupDTOPatch scheduleSetupRegularDTOPatch, Long scheduleSetupRegularId) {

        Setup existingSetup = scheduleSetupRegularRepository.findById(scheduleSetupRegularId)
                .orElseThrow(() -> new EntityNotFoundException("Setup not found with id: " + scheduleSetupRegularId));


        if(scheduleSetupRegularDTOPatch.getGroupId() != null){
            Group group = groupService.getEntityById(scheduleSetupRegularDTOPatch.getGroupId());
            existingSetup.setGroup(group);
        }

        if(scheduleSetupRegularDTOPatch.getCourseId() != null){
            Course course = courseService.getEntityById(scheduleSetupRegularDTOPatch.getCourseId());
            existingSetup.setCourse(course);
        }

        if(scheduleSetupRegularDTOPatch.getTeacherId() != null){
            Teacher teacher = teacherService.getEntityById(scheduleSetupRegularDTOPatch.getTeacherId());
            existingSetup.setTeacher(teacher);
        }

        if(scheduleSetupRegularDTOPatch.getCoursePriority() != null){
            existingSetup.setCoursePriority(scheduleSetupRegularDTOPatch.getCoursePriority());
        }

        if(scheduleSetupRegularDTOPatch.getHoursAWeek() != null){
            existingSetup.setHoursAWeek(scheduleSetupRegularDTOPatch.getHoursAWeek());
        }

        if(scheduleSetupRegularDTOPatch.getHoursTotal() != null){
            existingSetup.setHoursTotal(scheduleSetupRegularDTOPatch.getHoursTotal());
        }

        if(scheduleSetupRegularDTOPatch.getWeeksTotal() != null){
            existingSetup.setWeeksTotal(scheduleSetupRegularDTOPatch.getWeeksTotal());
        }

        if(scheduleSetupRegularDTOPatch.getHoursInLab() != null){
            existingSetup.setHoursInLab(scheduleSetupRegularDTOPatch.getHoursInLab());
        }

        if(scheduleSetupRegularDTOPatch.getPreferredRoomType() != null){
            existingSetup.setPreferredRoomType(scheduleSetupRegularDTOPatch.getPreferredRoomType());
        }

        return existingSetup;

    }
}
