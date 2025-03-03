package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.ScheduleSharedCourse;
import com.example.schedule_composer.utils.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleSharedCourseRepository extends JpaRepository<ScheduleSharedCourse, Long> {
    List<ScheduleSharedCourse> findAll();

}
