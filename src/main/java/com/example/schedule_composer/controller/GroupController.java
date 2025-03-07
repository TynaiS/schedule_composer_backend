package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.dto.patch.GroupDTOPatch;
import com.example.schedule_composer.dto.post.GroupDTOPost;
import com.example.schedule_composer.service.GroupService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.GROUP_API)
@Tag(name = "Group API", description = "Endpoints for managing student groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }



    @GetMapping("/{id}")
    @Operation(summary = "Get group by ID", description = "Retrieves a specific student group by its ID")
    public ResponseEntity<GroupDTOGet> getById(@PathVariable("id") Long id) {
        GroupDTOGet group = groupService.getById(id);
        return ResponseEntity.ok(group);
    }

    @GetMapping()
    @Operation(summary = "Get all groups", description = "Retrieves a list of all student groups")
    public ResponseEntity<List<GroupDTOGet>> getAll() {
        List<GroupDTOGet> groups = groupService.getAll();
        return ResponseEntity.ok(groups);
    }

    @PostMapping()
    @Operation(summary = "Create group", description = "Creates new group")
    public ResponseEntity<GroupDTOGet> create(
            @Valid @RequestBody GroupDTOPost request) {
        GroupDTOGet savedEntity = groupService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update group", description = "Updates an existing group")
    public ResponseEntity<GroupDTOGet> update(
            @PathVariable Long id,
            @RequestBody GroupDTOPatch patchRequest) {
        GroupDTOGet updated = groupService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete group by ID", description = "Deletes a specific group by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        groupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
