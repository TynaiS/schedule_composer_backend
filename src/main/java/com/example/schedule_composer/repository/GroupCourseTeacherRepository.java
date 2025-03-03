package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.GroupCourseTeacher;
import com.example.schedule_composer.utils.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupCourseTeacherRepository extends JpaRepository<GroupCourseTeacher, Long> {
    List<GroupCourseTeacher> findAll();

}
