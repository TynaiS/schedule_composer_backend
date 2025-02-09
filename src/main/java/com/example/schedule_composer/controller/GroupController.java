package com.example.schedule_composer.controller;

import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
@Tag(name = "Group API", description = "Endpoints for managing student groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping()
    @Operation(summary = "Get all groups", description = "Retrieves a list of all student groups")
    public List<Group> getGroups() {
        System.out.println(groupService.getGroups());
        return groupService.getGroups();
    }

    @GetMapping("{groupId}")
    @Operation(summary = "Get group by ID", description = "Retrieves a specific group by its ID")
    public Group getGroupById(@PathVariable("groupId") Long id) {
        System.out.println(groupService.getGroupById(id).getName());
        return groupService.getGroupById(id);
    }

}
