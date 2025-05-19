package com.example.schedule_composer.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseDTOGet {
    private Long id;
    private Long scheduleId;
    private String name;
    private Integer credits;
}
