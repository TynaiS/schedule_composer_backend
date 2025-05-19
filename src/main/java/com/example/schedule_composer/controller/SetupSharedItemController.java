package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.SetupSharedItemDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedItemDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.SetupSharedItemService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.SCHEDULE_API + "/{scheduleId}" +
        ApiConstants.SCHEDULE_VERSION_API + "/{scheduleVersionId}" +
        ApiConstants.SETUP_SHARED_SET_API + "/{setupSharedSetId}" +
        ApiConstants.SETUP_SHARED_ITEM_API
)
@Tag(name = "SetupSharedItem API", description = "Endpoints for managing SetupSharedItem relations of SetupSharedSet of ScheduleVersion of Schedule")
public class SetupSharedItemController {

    private final SetupSharedItemService setupSharedItemService;
    @GetMapping("/{setupSharedItemId}")
    @Operation(summary = "Get SetupSharedItem by ID", description = "Retrieves a specific SetupSharedItem by its ID for SetupSharedSet")
    public ResponseEntity<SetupSharedItemDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("scheduleVersionId") Long scheduleVersionId,
            @PathVariable("setupSharedSetId") Long setupSharedSetId,
            @PathVariable("setupSharedItemId") Long setupSharedItemId) {
        Long userId = user.getId();
        SetupSharedItemDTOGet result = setupSharedItemService.getByIdForUserSetupSharedSet(userId, scheduleId, scheduleVersionId, setupSharedSetId, setupSharedItemId);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    @Operation(summary = "Get all SetupSharedItems", description = "Retrieves a list of all SetupSharedItem's for SetupSharedSet")
    public ResponseEntity<List<SetupSharedItemDTOGet>> getAll(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("scheduleVersionId") Long scheduleVersionId,
            @PathVariable("setupSharedSetId") Long setupSharedSetId
            ) {
        Long userId = user.getId();
        List<SetupSharedItemDTOGet> result = setupSharedItemService.getAllForUserSetupSharedSet(userId, scheduleId, scheduleVersionId, setupSharedSetId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(ApiConstants.GROUP_API + "/{groupId}")
    @Operation(summary = "Get all SetupSharedItems by groupId", description = "Retrieves a list of all SetupSharedItem 's with specific groupId for SetupSharedSet")
    public ResponseEntity<List<SetupSharedItemDTOGet>> getAllByGroupId(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("scheduleVersionId") Long scheduleVersionId,
            @PathVariable("setupSharedSetId") Long setupSharedSetId,
            @PathVariable("groupId") Long groupId) {
        Long userId = user.getId();
        List<SetupSharedItemDTOGet> result = setupSharedItemService.getAllByGroupIdForUserSetupSharedSet(userId, scheduleId, scheduleVersionId, setupSharedSetId, groupId);
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    @Operation(summary = "Create SetupSharedItem relation", description = "Creates new SetupSharedItem relation for SetupSharedSet")
    public ResponseEntity<SetupSharedItemDTOGet> create(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("scheduleVersionId") Long scheduleVersionId,
            @PathVariable("setupSharedSetId") Long setupSharedSetId,
            @Valid @RequestBody SetupSharedItemDTOPost request) {
        Long userId = user.getId();
        SetupSharedItemDTOGet saved = setupSharedItemService.createForUserSetupSharedSet(userId, scheduleId, scheduleVersionId, setupSharedSetId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PatchMapping("/{setupSharedItemId}")
    @Operation(summary = "Update SetupSharedItem relation", description = "Updates an existing SetupSharedItem relation for SetupSharedSet")
    public ResponseEntity<SetupSharedItemDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("scheduleVersionId") Long scheduleVersionId,
            @PathVariable("setupSharedSetId") Long setupSharedSetId,
            @PathVariable("setupSharedItemId") Long setupSharedItemId,
            @Valid @RequestBody SetupSharedItemDTOPatch patchRequest) {
        Long userId = user.getId();
        SetupSharedItemDTOGet updated = setupSharedItemService.updateForUserSetupSharedSet(userId, scheduleId, scheduleVersionId, setupSharedSetId, setupSharedItemId, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{setupSharedItemId}")
    @Operation(summary = "Delete SetupSharedItem by ID", description = "Deletes a specific SetupSharedItem by its ID for SetupSharedSet")
    public ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("scheduleVersionId") Long scheduleVersionId,
            @PathVariable("setupSharedSetId") Long setupSharedSetId,
            @PathVariable("setupSharedItemId") Long setupSharedItemId) {
        Long userId = user.getId();
        setupSharedItemService.deleteByIdForUserSetupSharedSet(userId, scheduleId, scheduleVersionId, setupSharedSetId, setupSharedItemId);
        return ResponseEntity.noContent().build();
    }




//  ----  Shared groups ----
//
//
//
//    @GetMapping("/{setupSharedItemId}/groups/{group_id}")
//    @Operation(summary = "Get shared-group of specific setup-shared by ID", description = "Retrieves a specific shared-group of specific setup-shared by its ID")
//    public ResponseEntity<GroupDTOGet> getById(
//            @PathVariable("setupSharedItemId") Long setupSharedItemId,
//            @PathVariable("group_id") Long group_id) {
//        GroupDTOGet result = setupSharedItemService.getGroupById(setupSharedItemId, group_id);
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/{setupSharedItemId}/groups")
//    @Operation(summary = "Get all shared-group of specific setup-shared", description = "Retrieves a list of all shared-group of specific setup-shared")
//    public ResponseEntity<List<GroupDTOGet>> getAll(
//            @PathVariable("setupSharedItemId") Long setupSharedItemId) {
//        List<GroupDTOGet> result = setupSharedItemService.getAllGroups(setupSharedItemId);
//        return ResponseEntity.ok(result);
//    }
//
//
//    @PostMapping("/{setupSharedItemId}/groups")
//    @Operation(summary = "Add new shared-group to specific setup-shared", description = "Creates new shared-group of specific setup-shared")
//    public ResponseEntity<GroupDTOGet> create(
//            Long group_id,
//            @PathVariable("setupSharedItemId") Long setupSharedItemId) {
//        GroupDTOGet savedEntity = setupSharedItemService.addGroup(setupSharedItemId, group_id);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
//    }
//
//    @DeleteMapping("/{setupSharedItemId}/groups/{group_id}")
//    @Operation(summary = "Delete shared-group of specific setup-shared by ID", description = "Deletes a specific shared-group of specific setup-shared by its ID")
//    public ResponseEntity<Void> deleteGroupById(
//            @PathVariable("setupSharedItemId") Long setupSharedItemId,
//            @PathVariable("group_id") Long group_id) {
//        setupSharedItemService.deleteGroupById(setupSharedItemId, group_id);
//        return ResponseEntity.noContent().build();
//    }
}
