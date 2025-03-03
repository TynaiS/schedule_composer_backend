package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.CourseTeacherShared;
import com.example.schedule_composer.utils.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTeacherSharedRepository extends JpaRepository<CourseTeacherShared, Long> {
    List<CourseTeacherShared> findAll();

}
