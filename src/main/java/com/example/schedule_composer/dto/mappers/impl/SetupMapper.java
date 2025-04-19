package com.example.schedule_composer.dto.mappers.impl;

import com.example.schedule_composer.dto.get.SetupDTOGet;
import com.example.schedule_composer.dto.mappers.DTOMapper;
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
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SetupMapper implements DTOMapper<SetupDTOGet, SetupDTOPost, SetupDTOPatch, Setup, Long> {

    private final SetupRepository setupRepository;
    private final GroupService groupService;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final GroupMapper groupMapper;
    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;

    @Autowired
    public SetupMapper(SetupRepository setupRepository, GroupService groupService, CourseService courseService, TeacherService teacherService, GroupMapper groupMapper, CourseMapper courseMapper, TeacherMapper teacherMapper) {
        this.setupRepository = setupRepository;
        this.groupService = groupService;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.groupMapper = groupMapper;
        this.courseMapper = courseMapper;
        this.teacherMapper = teacherMapper;
    }
    @Override
    public SetupDTOGet fromEntityToGet(Setup setup) {
        SetupDTOGet setupGet = new SetupDTOGet(
                setup.getId(),
                groupMapper.fromEntityToGet(setup.getGroup()),
                courseMapper.fromEntityToGet(setup.getCourse()),
                teacherMapper.fromEntityToGet(setup.getTeacher()),
                setup.getCoursePriority(),
                setup.getHoursAWeek(),
//                setup.getHoursTotal(),
//                setup.getWeeksTotal(),
                setup.getHoursInLab(),
                setup.getPreferredRoomType());
        return setupGet;
    }

    @Override
    public List<SetupDTOGet> fromEntityListToGetList(List<Setup> setups) {
        return setups.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public Setup fromPostToEntity(SetupDTOPost setupDTOPost) {
        Group group = groupService.getEntityById(setupDTOPost.getGroupId());
        Course course = courseService.getEntityById(setupDTOPost.getCourseId());
        Teacher teacher = teacherService.getEntityById(setupDTOPost.getTeacherId());

        Setup setup = Setup.builder()
                .group(group)
                .course(course)
                .teacher(teacher)
                .coursePriority(setupDTOPost.getCoursePriority())
                .hoursAWeek(setupDTOPost.getHoursAWeek())
//                .hoursTotal(setupDTOPost.getHoursTotal())
//                .weeksTotal(setupDTOPost.getWeeksTotal())
                .hoursInLab(setupDTOPost.getHoursInLab())
                .preferredRoomType(setupDTOPost.getPreferredRoomType())
                .build();
        return setup;
    }

    @Override
    public Setup fromPatchToEntity(SetupDTOPatch setupDTOPatch, Long setupId) {

        Setup existingSetup = setupRepository.findById(setupId)
                .orElseThrow(() -> new EntityNotFoundException("Setup not found with id: " + setupId));


        if(setupDTOPatch.getGroupId() != null){
            Group group = groupService.getEntityById(setupDTOPatch.getGroupId());
            existingSetup.setGroup(group);
        }

        if(setupDTOPatch.getCourseId() != null){
            Course course = courseService.getEntityById(setupDTOPatch.getCourseId());
            existingSetup.setCourse(course);
        }

        if(setupDTOPatch.getTeacherId() != null){
            Teacher teacher = teacherService.getEntityById(setupDTOPatch.getTeacherId());
            existingSetup.setTeacher(teacher);
        }

        if(setupDTOPatch.getCoursePriority() != null){
            existingSetup.setCoursePriority(setupDTOPatch.getCoursePriority());
        }

        if(setupDTOPatch.getHoursAWeek() != null){
            existingSetup.setHoursAWeek(setupDTOPatch.getHoursAWeek());
        }

//        if(setupDTOPatch.getHoursTotal() != null){
//            existingSetup.setHoursTotal(setupDTOPatch.getHoursTotal());
//        }
//
//        if(setupDTOPatch.getWeeksTotal() != null){
//            existingSetup.setWeeksTotal(setupDTOPatch.getWeeksTotal());
//        }

        if(setupDTOPatch.getHoursInLab() != null){
            existingSetup.setHoursInLab(setupDTOPatch.getHoursInLab());
        }

        if(setupDTOPatch.getPreferredRoomType() != null){
            existingSetup.setPreferredRoomType(setupDTOPatch.getPreferredRoomType());
        }

        return existingSetup;

    }
}
