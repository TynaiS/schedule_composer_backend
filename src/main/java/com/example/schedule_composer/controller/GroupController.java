package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.dto.patch.GroupDTOPatch;
import com.example.schedule_composer.dto.post.GroupDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.GroupService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.SCHEDULE_API + "/{scheduleId}" + ApiConstants.GROUP_API)
@Tag(name = "Group API", description = "Endpoints for managing student Groups inside of Schedule")
public class GroupController {

    private final GroupService groupService;



    @GetMapping("/{groupId}")
    @Operation(summary = "Get Group by ID", description = "Retrieves a specific student Group by its ID for Schedule")
    public ResponseEntity<GroupDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("groupId") Long groupId) {
        Long userId = user.getId();
        GroupDTOGet group = groupService.getByIdForUserSchedule(userId, scheduleId, groupId);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    @Operation(summary = "Get all Groups", description = "Retrieves a list of all student Groups for Schedule")
    public ResponseEntity<List<GroupDTOGet>> getAll(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId) {
        Long userId = user.getId();
        List<GroupDTOGet> groups = groupService.getAllForUserSchedule(userId, scheduleId);
        return ResponseEntity.ok(groups);
    }

    @PostMapping
    @Operation(summary = "Create Group", description = "Creates a new student Group for Schedule")
    public ResponseEntity<GroupDTOGet> create(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @Valid @RequestBody GroupDTOPost request) {
        Long userId = user.getId();
        GroupDTOGet savedGroup = groupService.createForUserSchedule(userId, scheduleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroup);
    }

    @PatchMapping("/{groupId}")
    @Operation(summary = "Update Group", description = "Updates an existing student Group for Schedule")
    public ResponseEntity<GroupDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("groupId") Long groupId,
            @Valid @RequestBody GroupDTOPatch patchRequest) {
        Long userId = user.getId();
        GroupDTOGet updatedGroup = groupService.updateForUserSchedule(userId, scheduleId, groupId, patchRequest);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/{groupId}")
    @Operation(summary = "Delete Group by ID", description = "Deletes a specific student Group by its ID for Schedule")
    public ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("groupId") Long groupId) {
        Long userId = user.getId();
        groupService.deleteByIdForUserSchedule(userId, scheduleId, groupId);
        return ResponseEntity.noContent().build();
    }

}
