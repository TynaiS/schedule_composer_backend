package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.SetupSharedName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetupSharedNameRepository extends JpaRepository<SetupSharedName, Long> {
    List<SetupSharedName> findAll();
}
