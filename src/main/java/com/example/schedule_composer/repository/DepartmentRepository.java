package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Override
    List<Department> findAll();

    List<Department> findAllByScheduleId(Long scheduleId);
}
