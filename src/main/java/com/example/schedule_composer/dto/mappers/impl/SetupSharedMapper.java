package com.example.schedule_composer.dto.mappers.impl;

import com.example.schedule_composer.dto.get.SetupSharedDTOGet;
import com.example.schedule_composer.dto.mappers.DTOMapper;
import com.example.schedule_composer.dto.patch.SetupSharedDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.service.CourseService;
import com.example.schedule_composer.repository.SetupSharedRepository;
import com.example.schedule_composer.service.GroupService;
import com.example.schedule_composer.service.SetupSharedSetService;
import com.example.schedule_composer.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SetupSharedMapper implements DTOMapper<SetupSharedDTOGet, SetupSharedDTOPost, SetupSharedDTOPatch, SetupShared, Long> {

    private final SetupSharedRepository setupSharedRepository;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final SetupSharedSetService setupSharedSetService;
    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;
    private final GroupMapper groupMapper;
    private final SetupSharedSetMapper setupSharedSetMapper;


    @Override
    public SetupSharedDTOGet fromEntityToGet(SetupShared setupShared) {
        SetupSharedDTOGet setupSharedGet = new SetupSharedDTOGet(
                setupShared.getId(),
                setupSharedSetMapper.fromEntityToGet(setupShared.getSet()),
                groupMapper.fromEntityListToGetList(setupShared.getGroups()),
                courseMapper.fromEntityToGet(setupShared.getCourse()),
                teacherMapper.fromEntityToGet(setupShared.getTeacher()),
                setupShared.getCoursePriority(),
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
        SetupSharedSet set = setupSharedSetService.getEntityById(setupSharedDTOPost.getSetId());
        Course course = courseService.getEntityById(setupSharedDTOPost.getCourseId());
        Teacher teacher = teacherService.getEntityById(setupSharedDTOPost.getTeacherId());
        List<Group> groups = groupService.checkIfAllExistAndGetEntities(setupSharedDTOPost.getGroupIds());


        SetupShared setupShared = SetupShared.builder()
                .set(set)
                .groups(groups)
                .course(course)
                .teacher(teacher)
                .coursePriority(setupSharedDTOPost.getCoursePriority())
                .hoursInLab(setupSharedDTOPost.getHoursInLab())
                .preferredRoomType(setupSharedDTOPost.getPreferredRoomType())
                .build();
        return setupShared;
    }

    @Override
    public SetupShared fromPatchToEntity(SetupSharedDTOPatch setupSharedDTOPatch, Long setupSharedId) {

        SetupShared existingSetupShared = setupSharedRepository.findById(setupSharedId)
                .orElseThrow(() -> new EntityNotFoundException("Setup-Shared not found with id: " + setupSharedId));


        if (setupSharedDTOPatch.getSetId() != null){
            SetupSharedSet set = setupSharedSetService.checkIfExistsAndGetEntity(setupSharedDTOPatch.getSetId());
            existingSetupShared.setSet(set);
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

        if(setupSharedDTOPatch.getHoursInLab() != null){
            existingSetupShared.setHoursInLab(setupSharedDTOPatch.getHoursInLab());
        }

        if(setupSharedDTOPatch.getPreferredRoomType() != null){
            existingSetupShared.setPreferredRoomType(setupSharedDTOPatch.getPreferredRoomType());
        }

        return existingSetupShared;

    }
}
