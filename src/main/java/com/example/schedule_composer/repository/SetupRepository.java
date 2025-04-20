package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.Setup;
import com.example.schedule_composer.utils.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetupRepository extends JpaRepository<Setup, Long> {
    List<Setup> findAll();

    List<Setup> findByGroupId(Long groupId);
}
