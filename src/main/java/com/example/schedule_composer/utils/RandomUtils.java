package com.example.schedule_composer.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static int randomIndex(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be > 0 to get a random index.");
        }
        return ThreadLocalRandom.current().nextInt(size);
    }
}

