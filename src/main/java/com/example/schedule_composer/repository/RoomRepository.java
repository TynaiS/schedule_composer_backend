package com.example.schedule_composer.repository;


import com.example.schedule_composer.entity.Room;
import com.example.schedule_composer.utils.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Override
    List<Room> findAll();
    List<Room> findByType(RoomType type);

}
