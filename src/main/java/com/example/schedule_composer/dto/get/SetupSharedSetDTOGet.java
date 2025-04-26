package com.example.schedule_composer.dto.get;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SetupSharedSetDTOGet {
    private Long id;
    private String name;
    private Integer hoursAWeek;
}

