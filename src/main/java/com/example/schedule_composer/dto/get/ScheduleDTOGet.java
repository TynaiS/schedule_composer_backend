package com.example.schedule_composer.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ScheduleDTOGet {

    private Long id;
    private String name;
    private List<String> editorsEmails;
}
