package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.entity.SetupShared;
import com.example.schedule_composer.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SharedGroupDTOGet {

    private Long id;
    private SetupSharedDTOGet scheduleSetupShared;
    private Group group;
}
