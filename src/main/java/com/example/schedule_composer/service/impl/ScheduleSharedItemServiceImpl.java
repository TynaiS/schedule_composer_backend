package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.ScheduleSharedItemDTOGet;
import com.example.schedule_composer.mappers.impl.ScheduleSharedItemMapper;
import com.example.schedule_composer.dto.patch.ScheduleSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedItemDTOPost;
import com.example.schedule_composer.entity.ScheduleSharedItem;
import com.example.schedule_composer.repository.ScheduleSharedItemRepository;
import com.example.schedule_composer.service.ScheduleSharedItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleSharedItemServiceImpl implements ScheduleSharedItemService {

    private final ScheduleSharedItemRepository scheduleSharedItemRepository;
    private final ScheduleSharedItemMapper scheduleSharedItemMapper;

    @Autowired
    public ScheduleSharedItemServiceImpl(ScheduleSharedItemRepository scheduleSharedItemRepository, ScheduleSharedItemMapper scheduleSharedItemMapper){
        this.scheduleSharedItemRepository = scheduleSharedItemRepository;
        this.scheduleSharedItemMapper = scheduleSharedItemMapper;
    }

    @Override
    public ScheduleSharedItemDTOGet getById(Long id) {
        return scheduleSharedItemMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public ScheduleSharedItem getEntityById(Long id) {
        ScheduleSharedItem entity = scheduleSharedItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleItem-Shared-Course item not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleSharedItemRepository.existsById(id)) {
            throw new EntityNotFoundException("ScheduleItem-Shared-Course item not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<ScheduleSharedItemDTOGet> getAll() {
        List<ScheduleSharedItem> entities = scheduleSharedItemRepository.findAll();

        return scheduleSharedItemMapper.fromEntityListToGetList(entities);
    }

    @Override
    public List<ScheduleSharedItem> getAllEntities() {
        return scheduleSharedItemRepository.findAll();
    }

    @Override
    public ScheduleSharedItemDTOGet create(ScheduleSharedItemDTOPost createDto) {
        ScheduleSharedItem savedEntity = scheduleSharedItemRepository.save(scheduleSharedItemMapper.fromPostToEntity(createDto));
        return scheduleSharedItemMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleSharedItemDTOGet create(ScheduleSharedItem createEntity) {
        ScheduleSharedItem savedEntity = scheduleSharedItemRepository.save(createEntity);
        return scheduleSharedItemMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleSharedItemDTOGet update(Long id, ScheduleSharedItemDTOPatch updateDto) {
        ScheduleSharedItem updatedEntity = scheduleSharedItemRepository.save(scheduleSharedItemMapper.fromPatchToEntity(updateDto, id));
        return scheduleSharedItemMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleSharedItemRepository.deleteById(id);
    }
}
