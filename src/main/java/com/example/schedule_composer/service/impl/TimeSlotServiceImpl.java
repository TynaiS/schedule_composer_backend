package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.mappers.TimeSlotMapper;
import com.example.schedule_composer.dto.patch.TimeSlotDTOPatch;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.repository.TimeSlotRepository;
import com.example.schedule_composer.service.TimeSlotService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotMapper timeSlotMapper;

    @Autowired
    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository,TimeSlotMapper timeSlotMapper){
        this.timeSlotRepository = timeSlotRepository;
        this.timeSlotMapper = timeSlotMapper;
    }

    @Override
    public TimeSlotDTOGet getById(Long id) {
        return timeSlotMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public TimeSlot getEntityById(Long id) {
        TimeSlot entity = timeSlotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Time slot not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!timeSlotRepository.existsById(id)) {
            throw new EntityNotFoundException("Time slot not found with id: " + id);
        }
        return true;
    }


    @Override
    public List<TimeSlotDTOGet> getAll() {
        List<TimeSlot> entities = timeSlotRepository.findAll();

        return entities.stream()
                .map(timeSlotMapper::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public TimeSlotDTOGet create(TimeSlotDTOPost createDto) {
        TimeSlot savedEntity = timeSlotRepository.save(timeSlotMapper.fromPostToEntity(createDto));
        return timeSlotMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public TimeSlotDTOGet update(Long id, TimeSlotDTOPatch updateDto) {
        TimeSlot updatedEntity = timeSlotRepository.save(timeSlotMapper.fromPatchToEntity(updateDto, id));
        return timeSlotMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) timeSlotRepository.deleteById(id);
    }
}
