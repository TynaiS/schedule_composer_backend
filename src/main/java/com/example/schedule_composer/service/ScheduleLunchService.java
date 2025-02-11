package com.example.schedule_composer.service;

import com.example.schedule_composer.entity.ScheduleLunch;
import com.example.schedule_composer.repository.ScheduleLunchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleLunchService {

    private final ScheduleLunchRepository scheduleLunchRepository;

    @Autowired
    public ScheduleLunchService(ScheduleLunchRepository scheduleLunchRepository) {
        this.scheduleLunchRepository = scheduleLunchRepository;
    }

    public List<ScheduleLunch> getScheduleLunches() {
        return scheduleLunchRepository.findAll();
    }

    public ScheduleLunch getScheduleLunchById(Long id) {
        return scheduleLunchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Schedule lunch not found with id " + id));
    }
}
