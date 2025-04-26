package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.SetupShared;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetupSharedRepository extends JpaRepository<SetupShared, Long> {
    @Override
    List<SetupShared> findAll();

    List<SetupShared> findByGroupsId(Long groupId);

}
