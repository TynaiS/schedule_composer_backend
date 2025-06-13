package com.example.schedule_composer.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static int randomIndex(int size) {
        if (size <= 0) {
            return 0;
        }
        return ThreadLocalRandom.current().nextInt(size);
    }
}

