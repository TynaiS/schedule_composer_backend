package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.patch.GroupCourseTeacherDTOPatch;
import com.example.schedule_composer.dto.post.CourseTeacherSharedDTOPost;
import com.example.schedule_composer.dto.post.CourseTeacherSharedGroupDTOPost;
import com.example.schedule_composer.dto.post.GroupCourseTeacherDTOPost;
import com.example.schedule_composer.dto.get.CourseTeacherSharedDTOGet;
import com.example.schedule_composer.dto.get.CourseTeacherSharedGroupDTOGet;
import com.example.schedule_composer.dto.get.GroupCourseTeacherDTOGet;
import com.example.schedule_composer.repository.CourseTeacherSharedRepository;
import com.example.schedule_composer.repository.GroupCourseTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupCourseTeacherService implements CrudService<GroupCourseTeacherDTOGet, GroupCourseTeacherDTOPost, GroupCourseTeacherDTOPatch, Long>{

    private final GroupCourseTeacherRepository groupCourseTeacherRepository;
    private final CourseTeacherSharedRepository courseTeacherSharedRepository;
    private final GroupService groupService;
    private final CourseService courseService;
    private final TeacherService teacherService;

    @Autowired
    public GroupCourseTeacherService(
            GroupCourseTeacherRepository groupCourseTeacherRepository,
            CourseTeacherSharedRepository courseTeacherSharedRepository,
            GroupService groupService,
            CourseService courseService,
            TeacherService teacherService) {
        this.groupCourseTeacherRepository = groupCourseTeacherRepository;
        this.courseTeacherSharedRepository = courseTeacherSharedRepository;
        this.groupService = groupService;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }



    @Override
    public GroupCourseTeacherDTOGet getById(Long id) {
//        return groupCourseTeacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("GroupCourseTeacher not found with id " + id));
        return null;
//        to be implemented
    }

    @Override
    public List<GroupCourseTeacherDTOGet> getAll() {
//        return groupCourseTeacherRepository.findAll();
        return null;
//        to be implemented
    }

    @Override
    @Transactional
    public GroupCourseTeacherDTOGet create(GroupCourseTeacherDTOPost request) {
//        GroupCourseTeacher newGroupCourseTeacher = GroupCourseTeacher.builder()
//                .group(groupService.getGroupById(request.getGroupId()))
//                .course(courseService.getCourseById(request.getCourseId()))
//                .teacher(teacherService.getTeacherById(request.getTeacherId()))
//                .coursePriority(request.getCoursePriority())
//                .hoursAWeek(request.getHoursAWeek())
//                .hoursTotal(request.getHoursTotal())
//                .weeksTotal(request.getWeeksTotal())
//                .hoursInLab(request.getHoursInLab())
//                .preferredRoomType(request.getPreferredRoomType())
//                .build();
//        GroupCourseTeacher savedGroupCourseTeacher = groupCourseTeacherRepository.save(newGroupCourseTeacher);
//        return savedGroupCourseTeacher;
        return null;
//        to be implemented
    }

    @Override
    @Transactional
    public GroupCourseTeacherDTOGet update(Long id, GroupCourseTeacherDTOPatch patchRequest) {
//        GroupCourseTeacher entity = groupCourseTeacherRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("GroupCourseTeacher not found"));
//
//        if (patchRequest.getGroupId() != null) {
//            entity.setGroup(groupService.getGroupById(patchRequest.getGroupId()));
//        }
//        if (patchRequest.getCourseId() != null) {
//            entity.setCourse(courseService.getCourseById(patchRequest.getCourseId()));
//        }
//        if (patchRequest.getTeacherId() != null) {
//            entity.setTeacher(teacherService.getTeacherById(patchRequest.getTeacherId()));
//        }
//        if (patchRequest.getCoursePriority() != null) {
//            entity.setCoursePriority(patchRequest.getCoursePriority());
//        }
//        if (patchRequest.getHoursAWeek() != null) {
//            entity.setHoursAWeek(patchRequest.getHoursAWeek());
//        }
//        if (patchRequest.getHoursTotal() != 0) {
//            entity.setHoursTotal(patchRequest.getHoursTotal());
//        }
//        if (patchRequest.getWeeksTotal() != 0) {
//            entity.setWeeksTotal(patchRequest.getWeeksTotal());
//        }
//        if (patchRequest.getHoursInLab() != 0) {
//            entity.setHoursInLab(patchRequest.getHoursInLab());
//        }
//
//        if (patchRequest.getPreferredRoomType() != null) {
//            entity.setPreferredRoomType(patchRequest.getPreferredRoomType());
//        }
//
//
//        return groupCourseTeacherRepository.save(entity);
        return null;
    }

    @Override
    public void deleteById(Long id) {
        groupCourseTeacherRepository.deleteById(id);
    }

}
