package com.example.schedule_composer.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepartmentDTOGet {
    private Long id;
    private Long scheduleId;
    private String name;
}
