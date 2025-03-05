package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleLunchDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleLunchDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchDTOPost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleLunchService implements CrudService<ScheduleLunchDTOGet, ScheduleLunchDTOPost, ScheduleLunchDTOPatch, Long> {
    @Override
    public ScheduleLunchDTOGet getById(Long aLong) {
        return null;
    }

    @Override
    public List<ScheduleLunchDTOGet> getAll() {
        return null;
    }

    @Override
    public ScheduleLunchDTOGet create(ScheduleLunchDTOPost createDto) {
        return null;
    }

    @Override
    public ScheduleLunchDTOGet update(Long aLong, ScheduleLunchDTOPatch updateDto) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
