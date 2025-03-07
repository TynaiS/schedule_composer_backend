package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.GroupCourseTeacherDTOGet;
import com.example.schedule_composer.dto.patch.GroupCourseTeacherDTOPatch;
import com.example.schedule_composer.dto.post.GroupCourseTeacherDTOPost;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.entity.GroupCourseTeacher;
import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.repository.GroupCourseTeacherRepository;
import com.example.schedule_composer.service.CourseService;
import com.example.schedule_composer.service.GroupService;
import com.example.schedule_composer.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class GroupCourseTeacherMapper implements DTOMapper<GroupCourseTeacherDTOGet, GroupCourseTeacherDTOPost, GroupCourseTeacherDTOPatch, GroupCourseTeacher, Long>{

    private final GroupCourseTeacherRepository groupCourseTeacherRepository;
    private final GroupService groupService;
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final GroupMapper groupMapper;
    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;

    @Autowired
    public GroupCourseTeacherMapper(GroupCourseTeacherRepository groupCourseTeacherRepository, GroupService groupService, CourseService courseService, TeacherService teacherService, GroupMapper groupMapper, CourseMapper courseMapper, TeacherMapper teacherMapper) {
        this.groupCourseTeacherRepository = groupCourseTeacherRepository;
        this.groupService = groupService;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.groupMapper = groupMapper;
        this.courseMapper = courseMapper;
        this.teacherMapper = teacherMapper;
    }
    @Override
    public GroupCourseTeacherDTOGet fromEntityToGet(GroupCourseTeacher groupCourseTeacher) {
        GroupCourseTeacherDTOGet groupCourseTeacherGet = new GroupCourseTeacherDTOGet(
                groupCourseTeacher.getId(),
                groupMapper.fromEntityToGet(groupCourseTeacher.getGroup()),
                courseMapper.fromEntityToGet(groupCourseTeacher.getCourse()),
                teacherMapper.fromEntityToGet(groupCourseTeacher.getTeacher()),
                groupCourseTeacher.getCoursePriority(),
                groupCourseTeacher.getHoursAWeek(),
                groupCourseTeacher.getHoursTotal(),
                groupCourseTeacher.getWeeksTotal(),
                groupCourseTeacher.getHoursInLab(),
                groupCourseTeacher.getPreferredRoomType());
        return groupCourseTeacherGet;
    }

    @Override
    public GroupCourseTeacher fromPostToEntity(GroupCourseTeacherDTOPost groupCourseTeacherDTOPost) {
        Group group = groupService.getEntityById(groupCourseTeacherDTOPost.getGroupId());
        Course course = courseService.getEntityById(groupCourseTeacherDTOPost.getCourseId());
        Teacher teacher = teacherService.getEntityById(groupCourseTeacherDTOPost.getTeacherId());

        GroupCourseTeacher groupCourseTeacher = GroupCourseTeacher.builder()
                .group(group)
                .course(course)
                .teacher(teacher)
                .coursePriority(groupCourseTeacherDTOPost.getCoursePriority())
                .hoursAWeek(groupCourseTeacherDTOPost.getHoursAWeek())
                .hoursTotal(groupCourseTeacherDTOPost.getHoursTotal())
                .weeksTotal(groupCourseTeacherDTOPost.getWeeksTotal())
                .hoursInLab(groupCourseTeacherDTOPost.getHoursInLab())
                .preferredRoomType(groupCourseTeacherDTOPost.getPreferredRoomType())
                .build();
        return groupCourseTeacher;
    }

    @Override
    public GroupCourseTeacher fromPatchToEntity(GroupCourseTeacherDTOPatch groupCourseTeacherDTOPatch, Long groupCourseTeacherId) {

        GroupCourseTeacher existingGroupCourseTeacher = groupCourseTeacherRepository.findById(groupCourseTeacherId)
                .orElseThrow(() -> new EntityNotFoundException("Group-Course-Teacher not found with id: " + groupCourseTeacherId));


        if(groupCourseTeacherDTOPatch.getGroupId() != null){
            Group group = groupService.getEntityById(groupCourseTeacherDTOPatch.getGroupId());
            existingGroupCourseTeacher.setGroup(group);
        }

        if(groupCourseTeacherDTOPatch.getCourseId() != null){
            Course course = courseService.getEntityById(groupCourseTeacherDTOPatch.getCourseId());
            existingGroupCourseTeacher.setCourse(course);
        }

        if(groupCourseTeacherDTOPatch.getTeacherId() != null){
            Teacher teacher = teacherService.getEntityById(groupCourseTeacherDTOPatch.getTeacherId());
            existingGroupCourseTeacher.setTeacher(teacher);
        }

        if(groupCourseTeacherDTOPatch.getCoursePriority() != null){
            existingGroupCourseTeacher.setCoursePriority(groupCourseTeacherDTOPatch.getCoursePriority());
        }

        if(groupCourseTeacherDTOPatch.getHoursAWeek() != null){
            existingGroupCourseTeacher.setHoursAWeek(groupCourseTeacherDTOPatch.getHoursAWeek());
        }

        if(groupCourseTeacherDTOPatch.getHoursTotal() != null){
            existingGroupCourseTeacher.setHoursTotal(groupCourseTeacherDTOPatch.getHoursTotal());
        }

        if(groupCourseTeacherDTOPatch.getWeeksTotal() != null){
            existingGroupCourseTeacher.setWeeksTotal(groupCourseTeacherDTOPatch.getWeeksTotal());
        }

        if(groupCourseTeacherDTOPatch.getHoursInLab() != null){
            existingGroupCourseTeacher.setHoursInLab(groupCourseTeacherDTOPatch.getHoursInLab());
        }

        if(groupCourseTeacherDTOPatch.getPreferredRoomType() != null){
            existingGroupCourseTeacher.setPreferredRoomType(groupCourseTeacherDTOPatch.getPreferredRoomType());
        }

        return existingGroupCourseTeacher;

    }
}
