package com.example.schedule_composer.dto.mappers.impl;

import com.example.schedule_composer.dto.get.SetupSharedDTOGet;
import com.example.schedule_composer.dto.mappers.DTOMapper;
import com.example.schedule_composer.dto.patch.SetupSharedDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.service.CourseService;
import com.example.schedule_composer.repository.SetupSharedRepository;
import com.example.schedule_composer.service.GroupService;
import com.example.schedule_composer.service.SetupSharedNameService;
import com.example.schedule_composer.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SetupSharedMapper implements DTOMapper<SetupSharedDTOGet, SetupSharedDTOPost, SetupSharedDTOPatch, SetupShared, Long> {

    private final SetupSharedRepository setupSharedRepository;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final SetupSharedNameService setupSharedNameService;
    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;
    private final GroupMapper groupMapper;
    private final SetupSharedNameMapper setupSharedNameMapper;


    @Autowired
    public SetupSharedMapper(SetupSharedRepository setupSharedRepository, CourseService courseService, TeacherService teacherService, GroupService groupService, SetupSharedNameService setupSharedNameService, CourseMapper courseMapper, TeacherMapper teacherMapper, GroupMapper groupMapper, SetupSharedNameMapper setupSharedNameMapper) {
        this.setupSharedRepository = setupSharedRepository;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.groupService = groupService;
        this.setupSharedNameService = setupSharedNameService;
        this.courseMapper = courseMapper;
        this.teacherMapper = teacherMapper;
        this.groupMapper = groupMapper;
        this.setupSharedNameMapper = setupSharedNameMapper;
    }
    @Override
    public SetupSharedDTOGet fromEntityToGet(SetupShared setupShared) {
        SetupSharedDTOGet setupSharedGet = new SetupSharedDTOGet(
                setupShared.getId(),
                setupSharedNameMapper.fromEntityToGet(setupShared.getName()),
                groupMapper.fromEntityListToGetList(setupShared.getGroups()),
                courseMapper.fromEntityToGet(setupShared.getCourse()),
                teacherMapper.fromEntityToGet(setupShared.getTeacher()),
                setupShared.getCoursePriority(),
                setupShared.getHoursAWeek(),
//                setupShared.getHoursTotal(),
//                setupShared.getWeeksTotal(),
                setupShared.getHoursInLab(),
                setupShared.getPreferredRoomType());
        return setupSharedGet;
    }

    @Override
    public List<SetupSharedDTOGet> fromEntityListToGetList(List<SetupShared> setupShareds) {
        return setupShareds.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public SetupShared fromPostToEntity(SetupSharedDTOPost setupSharedDTOPost) {
        SetupSharedName name = setupSharedNameService.getEntityById(setupSharedDTOPost.getNameId());
        Course course = courseService.getEntityById(setupSharedDTOPost.getCourseId());
        Teacher teacher = teacherService.getEntityById(setupSharedDTOPost.getTeacherId());
        List<Group> groups = groupService.checkIfAllExistAndGetEntities(setupSharedDTOPost.getGroupIds());


        SetupShared setupShared = SetupShared.builder()
                .name(name)
                .groups(groups)
                .course(course)
                .teacher(teacher)
                .coursePriority(setupSharedDTOPost.getCoursePriority())
                .hoursAWeek(setupSharedDTOPost.getHoursAWeek())
//                .hoursTotal(setupSharedDTOPost.getHoursTotal())
//                .weeksTotal(setupSharedDTOPost.getWeeksTotal())
                .hoursInLab(setupSharedDTOPost.getHoursInLab())
                .preferredRoomType(setupSharedDTOPost.getPreferredRoomType())
                .build();
        return setupShared;
    }

    @Override
    public SetupShared fromPatchToEntity(SetupSharedDTOPatch setupSharedDTOPatch, Long setupSharedId) {

        SetupShared existingSetupShared = setupSharedRepository.findById(setupSharedId)
                .orElseThrow(() -> new EntityNotFoundException("Setup-Shared not found with id: " + setupSharedId));


        if (setupSharedDTOPatch.getNameId() != null){
            SetupSharedName name = setupSharedNameService.checkIfExistsAndGetEntity(setupSharedDTOPatch.getNameId());
            if(name.getName().isBlank()){
                throw new IllegalArgumentException("Setup-Shared name cannot be blank");
            }
            existingSetupShared.setName(name);
        }

        if(setupSharedDTOPatch.getGroupIds() != null){
            List<Group> groups = groupService.checkIfAllExistAndGetEntities(setupSharedDTOPatch.getGroupIds());
            existingSetupShared.setGroups(groups);
        }

        if(setupSharedDTOPatch.getCourseId() != null){
            Course course = courseService.getEntityById(setupSharedDTOPatch.getCourseId());
            existingSetupShared.setCourse(course);
        }

        if(setupSharedDTOPatch.getTeacherId() != null){
            Teacher teacher = teacherService.getEntityById(setupSharedDTOPatch.getTeacherId());
            existingSetupShared.setTeacher(teacher);
        }

        if(setupSharedDTOPatch.getCoursePriority() != null){
            existingSetupShared.setCoursePriority(setupSharedDTOPatch.getCoursePriority());
        }

        if(setupSharedDTOPatch.getHoursAWeek() != null){
            existingSetupShared.setHoursAWeek(setupSharedDTOPatch.getHoursAWeek());
        }

//        if(setupSharedDTOPatch.getHoursTotal() != null){
//            existingSetupShared.setHoursTotal(setupSharedDTOPatch.getHoursTotal());
//        }
//
//        if(setupSharedDTOPatch.getWeeksTotal() != null){
//            existingSetupShared.setWeeksTotal(setupSharedDTOPatch.getWeeksTotal());
//        }

        if(setupSharedDTOPatch.getHoursInLab() != null){
            existingSetupShared.setHoursInLab(setupSharedDTOPatch.getHoursInLab());
        }

        if(setupSharedDTOPatch.getPreferredRoomType() != null){
            existingSetupShared.setPreferredRoomType(setupSharedDTOPatch.getPreferredRoomType());
        }

        return existingSetupShared;

    }
}
