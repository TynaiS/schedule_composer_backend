package com.example.schedule_composer.dto.patch;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ScheduleDTOPatch {
    private String name;
    private List<String> editorsEmails;
}
