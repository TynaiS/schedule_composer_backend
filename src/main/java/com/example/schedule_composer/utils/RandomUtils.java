package com.example.schedule_composer.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static int randomFromOneTo(int max) {
        if (max < 1) {
            throw new IllegalArgumentException("max must be >= 1");
        }
        return ThreadLocalRandom.current().nextInt(1, max + 1);
    }
}

