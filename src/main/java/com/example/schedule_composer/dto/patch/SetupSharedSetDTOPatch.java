package com.example.schedule_composer.dto.patch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SetupSharedSetDTOPatch {
    private String name;
    private Integer hoursAWeek;

}
