package com.example.schedule_composer.dto.patch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTOPatch {
    private String name;
    private List<String> editorsEmails;
}
