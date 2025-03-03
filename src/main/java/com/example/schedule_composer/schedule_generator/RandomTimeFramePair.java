package com.example.schedule_composer.schedule_generator;

import java.time.LocalTime;
import java.util.Random;

public class RandomTimeFramePair {

    static class TimeFrame {
        LocalTime startTime;
        LocalTime endTime;

        TimeFrame(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return startTime + " - " + endTime;
        }
    }

    public static TimeFrame getRandomTimeFramePair(int academicHours) {

        TimeFrame[] timeFrames =
                switch (academicHours) {
                    case 1:
                        yield getOneAcademicHourTimeFrames();
                    case 2:
                        yield getTwoAcademicHourTimeFrames();
                    case 3:
                        yield getThreeAcademicHourTimeFrames();
                    case 4:
                        yield getFourAcademicHourTimeFrames();
                    default:
                        yield getOneAcademicHourTimeFrames();
                };


        Random random = new Random();
        int randomIndex = random.nextInt(timeFrames.length);

        TimeFrame randomTimeFrame = timeFrames[randomIndex];

        return randomTimeFrame;
    }



    private static TimeFrame[] getOneAcademicHourTimeFrames() {
        TimeFrame[] oneAcademicHourTimeFrames = {
                new TimeFrame(LocalTime.of(9, 0), LocalTime.of(9, 55)),
                new TimeFrame(LocalTime.of(10, 0), LocalTime.of(10, 40)),
                new TimeFrame(LocalTime.of(10, 45), LocalTime.of(11, 25)),
                new TimeFrame(LocalTime.of(11, 30), LocalTime.of(12, 10)),
                new TimeFrame(LocalTime.of(12, 15), LocalTime.of(12, 55)),
                new TimeFrame(LocalTime.of(13, 0), LocalTime.of(13, 40)),
                new TimeFrame(LocalTime.of(13, 45), LocalTime.of(14, 25)),
                new TimeFrame(LocalTime.of(14, 30), LocalTime.of(15, 10)),
                new TimeFrame(LocalTime.of(15, 15), LocalTime.of(15, 55)),
                new TimeFrame(LocalTime.of(16, 0), LocalTime.of(16, 40)),
                new TimeFrame(LocalTime.of(16, 45), LocalTime.of(17, 25)),
                new TimeFrame(LocalTime.of(17, 30), LocalTime.of(18, 10))
        };

        return oneAcademicHourTimeFrames;
    }

    private static TimeFrame[] getTwoAcademicHourTimeFrames() {
        TimeFrame[] twoAcademicHourTimeFrames = {
                new TimeFrame(LocalTime.of(9, 0), LocalTime.of(10, 40)),
                new TimeFrame(LocalTime.of(10, 0), LocalTime.of(11, 25)),
                new TimeFrame(LocalTime.of(10, 45), LocalTime.of(12, 10)),
                new TimeFrame(LocalTime.of(11, 30), LocalTime.of(12, 55)),
                new TimeFrame(LocalTime.of(12, 15), LocalTime.of(13, 40)),
                new TimeFrame(LocalTime.of(13, 0), LocalTime.of(14, 25)),
                new TimeFrame(LocalTime.of(13, 45), LocalTime.of(15, 10)),
                new TimeFrame(LocalTime.of(14, 30), LocalTime.of(15, 55)),
                new TimeFrame(LocalTime.of(15, 15), LocalTime.of(16, 40)),
                new TimeFrame(LocalTime.of(16, 0), LocalTime.of(17, 25)),
                new TimeFrame(LocalTime.of(16, 45), LocalTime.of(18, 10))
        };

        return twoAcademicHourTimeFrames;
    }

    private static TimeFrame[] getThreeAcademicHourTimeFrames() {
        TimeFrame[] threeAcademicHourTimeFrames = {
                new TimeFrame(LocalTime.of(9, 0), LocalTime.of(11, 25)),
                new TimeFrame(LocalTime.of(10, 0), LocalTime.of(12, 10)),
                new TimeFrame(LocalTime.of(10, 45), LocalTime.of(12, 55)),
                new TimeFrame(LocalTime.of(11, 30), LocalTime.of(13, 40)),
                new TimeFrame(LocalTime.of(12, 15), LocalTime.of(14, 25)),
                new TimeFrame(LocalTime.of(13, 0), LocalTime.of(15, 10)),
                new TimeFrame(LocalTime.of(13, 45), LocalTime.of(15, 55)),
                new TimeFrame(LocalTime.of(14, 30), LocalTime.of(16, 40)),
                new TimeFrame(LocalTime.of(15, 15), LocalTime.of(17, 25)),
                new TimeFrame(LocalTime.of(16, 0), LocalTime.of(18, 10))
        };

        return threeAcademicHourTimeFrames;
    }

    private static TimeFrame[] getFourAcademicHourTimeFrames() {
        TimeFrame[] fourAcademicHourTimeFrames = {
                new TimeFrame(LocalTime.of(9, 0), LocalTime.of(12, 10)),
                new TimeFrame(LocalTime.of(10, 0), LocalTime.of(12, 55)),
                new TimeFrame(LocalTime.of(10, 45), LocalTime.of(13, 40)),
                new TimeFrame(LocalTime.of(11, 30), LocalTime.of(14, 25)),
                new TimeFrame(LocalTime.of(12, 15), LocalTime.of(15, 10)),
                new TimeFrame(LocalTime.of(13, 0), LocalTime.of(15, 55)),
                new TimeFrame(LocalTime.of(13, 45), LocalTime.of(16, 40)),
                new TimeFrame(LocalTime.of(14, 30), LocalTime.of(17, 25)),
                new TimeFrame(LocalTime.of(15, 15), LocalTime.of(18, 10))
        };

        return fourAcademicHourTimeFrames;
    }
}
