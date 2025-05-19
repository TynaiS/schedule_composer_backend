package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.SetupSharedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetupSharedItemRepository extends JpaRepository<SetupSharedItem, Long> {
    @Override
    List<SetupSharedItem> findAll();

    List<SetupSharedItem> findByGroupsId(Long groupId);

    List<SetupSharedItem> findBySetupSharedSetId(Long setupSharedSetId);

    List<SetupSharedItem> findByGroupsIdAndSetupSharedSetId(Long groupId, Long setupSharedSetId);
}
