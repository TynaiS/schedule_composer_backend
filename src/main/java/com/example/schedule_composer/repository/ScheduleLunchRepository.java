package com.example.schedule_composer.repository;


import com.example.schedule_composer.entity.ScheduleLunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleLunchRepository extends JpaRepository<ScheduleLunch, Long> {
    List<ScheduleLunch> findAll();
}
