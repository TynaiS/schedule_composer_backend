package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.CourseTeacherSharedDTOPost;
import com.example.schedule_composer.dto.CourseTeacherSharedGroupDTOPost;
import com.example.schedule_composer.dto.GroupCourseTeacherDTOPost;
import com.example.schedule_composer.dto.get.CourseTeacherSharedDTOGet;
import com.example.schedule_composer.dto.get.CourseTeacherSharedGroupDTOGet;
import com.example.schedule_composer.dto.get.GroupCourseTeacherDTOGet;
import com.example.schedule_composer.service.GroupCourseTeacherService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.COURSE_GROUP_TEACHER_API)
@Tag(name = "GroupCourseTeacher API", description = "Endpoints for managing course-group-teacher relations, i.e. what courses and teachers assigned for specific groups")
public class GroupCourseTeacherController {

    private final GroupCourseTeacherService groupCourseTeacherService;

    @Autowired
    public GroupCourseTeacherController(GroupCourseTeacherService groupCourseTeacherService) {
        this.groupCourseTeacherService = groupCourseTeacherService;
    }

    @GetMapping()
    @Operation(summary = "Get all group_course_teacher", description = "Retrieves a list of all group_course_teacher's")
    public List<GroupCourseTeacherDTOGet> getGroupCourseTeachers() {
        System.out.println(groupCourseTeacherService.getGroupCourseTeachers());
        return groupCourseTeacherService.getGroupCourseTeachers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get group_course_teacher by ID", description = "Retrieves a specific group_course_teacher by its ID")
    public GroupCourseTeacherDTOGet getGroupCourseTeacherById(
            @PathVariable("id") Long id) {
        System.out.println(groupCourseTeacherService.getGroupCourseTeacherById(id) .getTeacher());
        return groupCourseTeacherService.getGroupCourseTeacherById(id);
    }

    @PostMapping
    @Operation(summary = "Create group_course_teacher relation", description = "Creates new group_course_teacher relation when you add specific course to group")
    public ResponseEntity<GroupCourseTeacherDTOPost> createGroupCourseTeacher(
            @RequestBody GroupCourseTeacherDTOPost request) {
        GroupCourseTeacherDTOPost savedEntity = groupCourseTeacherService.createGroupCourseTeacher(request);
        return ResponseEntity.ok(savedEntity);
    }

//    @PostMapping("/generate")
//    @Operation(summary = "Generates the schedule", description = "Generated the schedule")
//    public ResponseEntity<String> generateSchedule() {
//        return ResponseEntity.ok("Schedule created");
//    }

//    @PatchMapping("/{id}")
//    @Operation(summary = "Update group_course_teacher relation", description = "Updates an existing group_course_teacher relation")
//    public ResponseEntity<GroupCourseTeacher> patchGroupCourseTeacher(
//            @PathVariable Long id,
//            @RequestBody GroupCourseTeacherDTOPatch patchRequest) {
//        GroupCourseTeacher updated = groupCourseTeacherService.updateGroupCourseTeacher(id, patchRequest);
//        return ResponseEntity.ok(updated);
//    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete group_course_teacher by ID", description = "Deletes a specific group_course_teacher by its ID")
    public ResponseEntity<Void> deleteGroupCourseTeacher(@PathVariable("id") Long id) {
        groupCourseTeacherService.deleteGroupCourseTeacher(id);
        return ResponseEntity.noContent().build();
    }


// ==============================================================================



    @GetMapping("/shared")
    @Operation(summary = "Get all course_teacher_shared", description = "Retrieves a list of all course_teacher_shared's")
    public List<CourseTeacherSharedDTOGet> getCourseTeacherShareds() {
        return groupCourseTeacherService.getCourseTeacherShareds();
    }

    @GetMapping("/shared/{id}")
    @Operation(summary = "Get course_teacher_shared by ID", description = "Retrieves a specific course_teacher_shared by its ID")
    public CourseTeacherSharedDTOGet getCourseTeacherSharedById(
            @PathVariable("id") Long id) {
        return groupCourseTeacherService.getCourseTeacherSharedById(id);
    }

    @PostMapping("/shared")
    @Operation(summary = "Create course_teacher_shared relation", description = "Creates new course_teacher_shared relation you create new shared courses set")
    public ResponseEntity<CourseTeacherSharedDTOPost> createCourseTeacherShared(
            @RequestBody CourseTeacherSharedDTOPost request) {
        CourseTeacherSharedDTOPost savedEntity = groupCourseTeacherService.createCourseTeacherShared(request);
        return ResponseEntity.ok(savedEntity);
    }

//    @PatchMapping("/shared/{id}")
//    @Operation(summary = "Update course_teacher_shared relation", description = "Updates an existing course_teacher_shared relation")
//    public ResponseEntity<CourseTeacherShared> patchCourseTeacherShared(
//            @PathVariable Long id,
//            @RequestBody CourseTeacherSharedDTOPatch patchRequest) {
//        CourseTeacherShared updated = groupCourseTeacherService.updateCourseTeacherShared(id, patchRequest);
//        return ResponseEntity.ok(updated);
//    }


    @DeleteMapping("/shared/{id}")
    @Operation(summary = "Delete course_teacher_shared by ID", description = "Deletes a specific course_teacher_shared by its ID")
    public ResponseEntity<Void> deleteCourseTeacherShared(@PathVariable("id") Long id) {
        groupCourseTeacherService.deleteCourseTeacherShared(id);
        return ResponseEntity.noContent().build();
    }



// ==============================================================================




    @GetMapping("/shared_groups")
    @Operation(summary = "Get all course_teacher_shared_groups", description = "Retrieves a list of all course_teacher_shared_groups's")
    public List<CourseTeacherSharedGroupDTOGet> getCourseTeacherSharedGroups() {
        System.out.println(groupCourseTeacherService.getCourseTeacherSharedGroups());
        return groupCourseTeacherService.getCourseTeacherSharedGroups();
    }

    @GetMapping("/shared_groups/{id}")
    @Operation(summary = "Get course_teacher_shared_groups by ID", description = "Retrieves a specific course_teacher_shared_groups by its ID")
    public CourseTeacherSharedGroupDTOGet getCourseTeacherSharedGroupById(
            @PathVariable("id") Long id) {
        return groupCourseTeacherService.getCourseTeacherSharedGroupById(id);
    }

    @PostMapping("/shared_groups")
    @Operation(summary = "Create course_teacher_shared_groups relation", description = "Creates new course_teacher_shared_groups relation you add group to existing course_teacher_shared set")
    public ResponseEntity<CourseTeacherSharedGroupDTOPost> createCourseTeacherSharedGroup(
            @RequestBody CourseTeacherSharedGroupDTOPost request) {
        CourseTeacherSharedGroupDTOPost savedEntity = groupCourseTeacherService.createCourseTeacherSharedGroup(request);
        return ResponseEntity.ok(savedEntity);
    }

//    @PatchMapping("/shared_groups/{id}")
//    @Operation(summary = "Update course_teacher_shared_groups relation", description = "Updates an existing course_teacher_shared_groups relation")
//    public ResponseEntity<CourseTeacherSharedGroup> patchCourseTeacherSharedGroup(
//            @PathVariable Long id,
//            @RequestBody CourseTeacherSharedGroupDTOPatch patchRequest) {
//        CourseTeacherSharedGroup updated = groupCourseTeacherService.updateCourseTeacherSharedGroup(id, patchRequest);
//        return ResponseEntity.ok(updated);
//    }


    @DeleteMapping("/shared_groups/{id}")
    @Operation(summary = "Delete course_teacher_shared_groups by ID", description = "Deletes a specific course_teacher_shared_groups by its ID")
    public ResponseEntity<Void> deleteCourseTeacherSharedGroup(@PathVariable("id") Long id) {
        groupCourseTeacherService.deleteCourseTeacherSharedGroup(id);
        return ResponseEntity.noContent().build();
    }

}
