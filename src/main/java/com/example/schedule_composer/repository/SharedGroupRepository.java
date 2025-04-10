package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.SharedGroup;
import com.example.schedule_composer.utils.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharedGroupRepository extends JpaRepository<SharedGroup, Long> {
    List<SharedGroup> findAll();

}
