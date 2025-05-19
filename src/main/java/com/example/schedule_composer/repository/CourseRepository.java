package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Override
    List<Course> findAll();

    List<Course> findAllByScheduleId(Long scheduleId);
}
