package com.example.schedule_composer.dto.patch;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SetupSharedSetDTOPatch {
    private String name;
    private Integer hoursAWeek;

}
