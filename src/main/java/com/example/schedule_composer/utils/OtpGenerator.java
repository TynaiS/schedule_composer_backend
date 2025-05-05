package com.example.schedule_composer.utils;

import java.util.Random;

public class OtpGenerator {

    public static String otpGenerator() {
        Random random = new Random();
        return Integer.toString(random.nextInt(100_000, 999_999));
    }
}
