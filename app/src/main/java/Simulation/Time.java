package Simulation;

import java.time.LocalDateTime;

public final class Time {

    LocalDateTime factoryTime;

    private Time(
        int year,
        int month,
        int dayOfMonth,
        int hour,
        int minute,
        int second
    ) {
        LocalDateTime factoryTime = LocalDateTime.of(
            year,
            month,
            dayOfMonth,
            hour,
            minute,
            second
        );
        this.factoryTime = factoryTime;
    }
}
