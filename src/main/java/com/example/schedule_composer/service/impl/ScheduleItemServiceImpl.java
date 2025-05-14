package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.ScheduleItemDTOGet;
import com.example.schedule_composer.mappers.impl.ScheduleItemMapper;
import com.example.schedule_composer.dto.patch.ScheduleItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleItemDTOPost;
import com.example.schedule_composer.entity.ScheduleItem;
import com.example.schedule_composer.repository.ScheduleItemRepository;
import com.example.schedule_composer.service.ScheduleItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleItemServiceImpl implements ScheduleItemService {

    private final ScheduleItemRepository scheduleItemRepository;
    private final ScheduleItemMapper scheduleItemMapper;

    @Autowired
    public ScheduleItemServiceImpl(ScheduleItemRepository scheduleItemRepository, ScheduleItemMapper scheduleItemMapper){
        this.scheduleItemRepository = scheduleItemRepository;
        this.scheduleItemMapper = scheduleItemMapper;
    }

    @Override
    public ScheduleItemDTOGet getById(Long id) {
        return scheduleItemMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public ScheduleItem getEntityById(Long id) {
        ScheduleItem entity = scheduleItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleItem item not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleItemRepository.existsById(id)) {
            throw new EntityNotFoundException("ScheduleItem item not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<ScheduleItemDTOGet> getAll() {
        List<ScheduleItem> entities = scheduleItemRepository.findAll();

        return scheduleItemMapper.fromEntityListToGetList(entities);
    }

    @Override
    public List<ScheduleItem> getAllEntities() {
        return scheduleItemRepository.findAll();
    }

    @Override
    public ScheduleItemDTOGet create(ScheduleItemDTOPost createDto) {
        ScheduleItem savedEntity = scheduleItemRepository.save(scheduleItemMapper.fromPostToEntity(createDto));
        return scheduleItemMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleItemDTOGet create(ScheduleItem createEntity) {
        ScheduleItem savedEntity = scheduleItemRepository.save(createEntity);
        return scheduleItemMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleItemDTOGet update(Long id, ScheduleItemDTOPatch updateDto) {
        ScheduleItem updatedEntity = scheduleItemRepository.save(scheduleItemMapper.fromPatchToEntity(updateDto, id));
        return scheduleItemMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleItemRepository.deleteById(id);
    }
}
