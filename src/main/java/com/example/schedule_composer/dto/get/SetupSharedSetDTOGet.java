package com.example.schedule_composer.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SetupSharedSetDTOGet {
    private Long id;
    private Long scheduleVersionId;
    private String name;
    private Integer hoursAWeek;
}

