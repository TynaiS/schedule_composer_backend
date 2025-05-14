package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.ScheduleLunchItemDTOGet;
import com.example.schedule_composer.mappers.impl.ScheduleLunchItemMapper;
import com.example.schedule_composer.dto.patch.ScheduleLunchItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchItemDTOPost;
import com.example.schedule_composer.entity.ScheduleLunchItem;
import com.example.schedule_composer.repository.ScheduleLunchItemRepository;
import com.example.schedule_composer.service.ScheduleLunchItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleLunchItemServiceImpl implements ScheduleLunchItemService {

    private final ScheduleLunchItemRepository scheduleLunchItemRepository;
    private final ScheduleLunchItemMapper scheduleLunchItemMapper;

    @Autowired
    public ScheduleLunchItemServiceImpl(ScheduleLunchItemRepository scheduleLunchItemRepository, ScheduleLunchItemMapper scheduleLunchItemMapper){
        this.scheduleLunchItemRepository = scheduleLunchItemRepository;
        this.scheduleLunchItemMapper = scheduleLunchItemMapper;
    }

    @Override
    public ScheduleLunchItemDTOGet getById(Long id) {
        return scheduleLunchItemMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public ScheduleLunchItem getEntityById(Long id) {
        ScheduleLunchItem entity = scheduleLunchItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleItem lunch item not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleLunchItemRepository.existsById(id)) {
            throw new EntityNotFoundException("ScheduleItem lunch item not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<ScheduleLunchItemDTOGet> getAll() {
        List<ScheduleLunchItem> entities = scheduleLunchItemRepository.findAll();

        return scheduleLunchItemMapper.fromEntityListToGetList(entities);
    }

    @Override
    public List<ScheduleLunchItem> getAllEntities() {
        return scheduleLunchItemRepository.findAll();
    }

    @Override
    public ScheduleLunchItemDTOGet create(ScheduleLunchItemDTOPost createDto) {
        ScheduleLunchItem savedEntity = scheduleLunchItemRepository.save(scheduleLunchItemMapper.fromPostToEntity(createDto));
        return scheduleLunchItemMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleLunchItemDTOGet update(Long id, ScheduleLunchItemDTOPatch updateDto) {
        ScheduleLunchItem updatedEntity = scheduleLunchItemRepository.save(scheduleLunchItemMapper.fromPatchToEntity(updateDto, id));
        return scheduleLunchItemMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleLunchItemRepository.deleteById(id);
    }
}
