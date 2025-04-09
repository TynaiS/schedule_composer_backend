package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.CourseTeacherSharedGroupDTOGet;
import com.example.schedule_composer.dto.patch.CourseTeacherSharedGroupDTOPatch;
import com.example.schedule_composer.dto.post.CourseTeacherSharedGroupDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.repository.CourseTeacherSharedGroupRepository;
import com.example.schedule_composer.service.GroupService;
import com.example.schedule_composer.service.CourseTeacherSharedService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseTeacherSharedGroupMapper implements DTOMapper<CourseTeacherSharedGroupDTOGet, CourseTeacherSharedGroupDTOPost, CourseTeacherSharedGroupDTOPatch, CourseTeacherSharedGroup, Long>{

    private final CourseTeacherSharedGroupRepository courseTeacherSharedGroupRepository;
    private final GroupService groupService;
    private final CourseTeacherSharedService courseTeacherSharedService;
    private final CourseTeacherSharedMapper courseTeacherSharedMapper;

    @Autowired
    public CourseTeacherSharedGroupMapper(
            CourseTeacherSharedGroupRepository courseTeacherSharedGroupRepository,
            GroupService groupService,
            CourseTeacherSharedService courseTeacherSharedService,
            CourseTeacherSharedMapper courseTeacherSharedMapper) {
        this.courseTeacherSharedGroupRepository = courseTeacherSharedGroupRepository;
        this.groupService = groupService;
        this.courseTeacherSharedService = courseTeacherSharedService;
        this.courseTeacherSharedMapper = courseTeacherSharedMapper;
    }

    @Override
    public CourseTeacherSharedGroupDTOGet fromEntityToGet(CourseTeacherSharedGroup courseTeacherSharedGroup) {
        CourseTeacherSharedGroupDTOGet courseTeacherSharedGroupGet = new CourseTeacherSharedGroupDTOGet(courseTeacherSharedGroup.getId(), courseTeacherSharedMapper.fromEntityToGet(courseTeacherSharedGroup.getCourseTeacherShared()), courseTeacherSharedGroup.getGroup());
        return courseTeacherSharedGroupGet;
    }

    @Override
    public List<CourseTeacherSharedGroupDTOGet> fromEntityListToGetList(List<CourseTeacherSharedGroup> courseTeacherSharedGroups) {
        return courseTeacherSharedGroups.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public CourseTeacherSharedGroup fromPostToEntity(CourseTeacherSharedGroupDTOPost courseTeacherSharedGroupDTOPost) {
        CourseTeacherShared courseTeacherShared = courseTeacherSharedService.getEntityById(courseTeacherSharedGroupDTOPost.getCourseTeacherSharedId());
        Group group = groupService.getEntityById(courseTeacherSharedGroupDTOPost.getGroupId());

        CourseTeacherSharedGroup courseTeacherSharedGroup = CourseTeacherSharedGroup.builder()
                .courseTeacherShared(courseTeacherShared)
                .group(group)
                .build();
        return courseTeacherSharedGroup;
    }

    @Override
    public CourseTeacherSharedGroup fromPatchToEntity(CourseTeacherSharedGroupDTOPatch courseTeacherSharedGroupDTOPatch, Long courseTeacherSharedGroupId) {

        CourseTeacherSharedGroup existingCourseTeacherSharedGroup = courseTeacherSharedGroupRepository.findById(courseTeacherSharedGroupId)
                .orElseThrow(() -> new EntityNotFoundException("Course-Teacher-Shared-Group not found with id: " + courseTeacherSharedGroupId));

        if(courseTeacherSharedGroupDTOPatch.getCourseTeacherSharedId() != null){
            CourseTeacherShared courseTeacherShared = courseTeacherSharedService.getEntityById(courseTeacherSharedGroupDTOPatch.getCourseTeacherSharedId());
            existingCourseTeacherSharedGroup.setCourseTeacherShared(courseTeacherShared);
        }

        if(courseTeacherSharedGroupDTOPatch.getGroupId() != null){
            Group group = groupService.getEntityById(courseTeacherSharedGroupDTOPatch.getGroupId());
            existingCourseTeacherSharedGroup.setGroup(group);
        }

        return existingCourseTeacherSharedGroup;

    }
}
