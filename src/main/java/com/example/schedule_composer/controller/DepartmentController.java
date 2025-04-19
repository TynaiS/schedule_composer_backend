package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.DepartmentDTOGet;
import com.example.schedule_composer.dto.patch.DepartmentDTOPatch;
import com.example.schedule_composer.dto.post.DepartmentDTOPost;
import com.example.schedule_composer.service.DepartmentService;
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
@RequestMapping(ApiConstants.DEPARTMENT_API)
@Tag(name = "Department API", description = "Endpoints for managing student departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }



    @GetMapping("/{id}")
    @Operation(summary = "Get department by ID", description = "Retrieves a specific student department by its ID")
    public ResponseEntity<DepartmentDTOGet> getById(@PathVariable("id") Long id) {
        DepartmentDTOGet department = departmentService.getById(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping()
    @Operation(summary = "Get all departments", description = "Retrieves a list of all student departments")
    public ResponseEntity<List<DepartmentDTOGet>> getAll() {
        List<DepartmentDTOGet> departments = departmentService.getAll();
        return ResponseEntity.ok(departments);
    }

    @PostMapping()
    @Operation(summary = "Create department", description = "Creates new department")
    public ResponseEntity<DepartmentDTOGet> create(
            @Valid @RequestBody DepartmentDTOPost request) {
        DepartmentDTOGet savedEntity = departmentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update department", description = "Updates an existing department")
    public ResponseEntity<DepartmentDTOGet> update(
            @PathVariable Long id,
            @RequestBody DepartmentDTOPatch patchRequest) {
        DepartmentDTOGet updated = departmentService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete department by ID", description = "Deletes a specific department by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
