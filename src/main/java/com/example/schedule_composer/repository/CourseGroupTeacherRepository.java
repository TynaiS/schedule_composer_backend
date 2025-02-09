package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.CourseGroupTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseGroupTeacherRepository extends JpaRepository<CourseGroupTeacher, Long> {
    List<CourseGroupTeacher> findAll();
}
