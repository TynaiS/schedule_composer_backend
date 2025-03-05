package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.dto.patch.RoomDTOPatch;
import com.example.schedule_composer.dto.post.RoomDTOPost;
import com.example.schedule_composer.entity.Room;
import com.example.schedule_composer.repository.RoomRepository;
import com.example.schedule_composer.utils.RoomType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements CrudService<RoomDTOGet, RoomDTOPost, RoomDTOPatch, Long>{
    @Override
    public RoomDTOGet getById(Long aLong) {
        return null;
    }

    @Override
    public List<RoomDTOGet> getAll() {
        return null;
    }

    @Override
    public RoomDTOGet create(RoomDTOPost createDto) {
        return null;
    }

    @Override
    public RoomDTOGet update(Long aLong, RoomDTOPatch updateDto) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
