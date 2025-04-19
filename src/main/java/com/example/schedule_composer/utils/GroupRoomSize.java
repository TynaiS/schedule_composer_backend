package com.example.schedule_composer.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GroupRoomSize {
        MEDIUM,
        LARGE;

    @JsonCreator
    public static GroupRoomSize fromString(String value) {
        for (GroupRoomSize mode : values()) {
            if (mode.name().equalsIgnoreCase(value)) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Invalid group/room size: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();

    }
}
