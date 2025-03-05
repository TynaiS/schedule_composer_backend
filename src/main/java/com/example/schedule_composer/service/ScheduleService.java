package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleDTOGet;
import com.example.schedule_composer.dto.get.ScheduleLunchDTOGet;
import com.example.schedule_composer.dto.get.ScheduleSharedCourseDTOGet;
import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.repository.ScheduleLunchRepository;
import com.example.schedule_composer.repository.ScheduleRepository;
import com.example.schedule_composer.utils.TeachingMode;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class ScheduleService implements CrudService<ScheduleDTOGet, ScheduleDTOPost, ScheduleDTOPatch, Long>{
    @Override
    public ScheduleDTOGet getById(Long aLong) {
        return null;
    }

    @Override
    public List<ScheduleDTOGet> getAll() {
        return null;
    }

    @Override
    public ScheduleDTOGet create(ScheduleDTOPost createDto) {
        return null;
    }

    @Override
    public ScheduleDTOGet update(Long aLong, ScheduleDTOPatch updateDto) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
