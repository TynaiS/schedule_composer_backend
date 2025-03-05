package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.TimeSlotDTOPatch;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSlotService implements CrudService<TimeSlotDTOGet, TimeSlotDTOPost, TimeSlotDTOPatch, Long> {
    @Override
    public TimeSlotDTOGet getById(Long aLong) {
        return null;
    }

    @Override
    public List<TimeSlotDTOGet> getAll() {
        return null;
    }

    @Override
    public TimeSlotDTOGet create(TimeSlotDTOPost createDto) {
        return null;
    }

    @Override
    public TimeSlotDTOGet update(Long aLong, TimeSlotDTOPatch updateDto) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
