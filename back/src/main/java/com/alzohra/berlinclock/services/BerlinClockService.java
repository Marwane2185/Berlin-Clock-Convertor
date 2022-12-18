package com.alzohra.berlinclock.services;

import com.alzohra.berlinclock.exceptions.InvalidBerlinClockException;
import com.alzohra.berlinclock.exceptions.InvalidDigitalTimeException;
import com.alzohra.berlinclock.exceptions.NullParameterException;
import org.springframework.stereotype.Service;

/*
    This class contains our main service.
    Two main methods are defined.
    First one convert from digital time to berlin time.
    Second one convert from berlin time to digital time
    Here we provide support for 3 types of exceptions : empty or null value, not formatted strings for both digital time and berlin clock cases.
*/
@Service
public class BerlinClockService {

    private final String VALID_DIGITAL_TIME = "^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";
    private final String VALID_BERLIN_CLOCK = "^[YO][RO]{8}[YO]{2}[RO][YO]{2}[RO][YO]{2}[RO][YO]{6}$";

    public String getBerlinClockFromDigitalTime(String digitalTime) {
        /*
            handle null or empty value
        */

        if (digitalTime == null || digitalTime.length() == 0) {
            throw new NullParameterException("parameter is empty");
        }
        /*
            handle incorrect format case
        */

        if (!digitalTime.matches(VALID_DIGITAL_TIME)) {
            throw new InvalidDigitalTimeException("Digital time format is not correct");
        }

        /*
            Idea is to generate berlin clock string char by char while evaluating hours, minutes and seconds from digital time
        */

        StringBuilder berlinCLock = new StringBuilder();
        String[] dgTime = digitalTime.split(":");
        int seconds = Integer.parseInt(dgTime[2]);
        if (seconds % 2 == 0) {
            berlinCLock.append("Y");
        } else {
            berlinCLock.append("O");
        }
        int hours = Integer.parseInt(dgTime[0]);
        int fiveHoursChars = (hours - (hours % 5)) / 5;
        int i = 0;
        while (i < fiveHoursChars) {
            berlinCLock.append("R");
            i++;
        }
        while (i < 4) {
            berlinCLock.append("O");
            i++;
        }
        int oneHoursChars = hours % 5;
        while (i < 4 + oneHoursChars) {
            berlinCLock.append("R");
            i++;
        }
        while (i < 8) {
            berlinCLock.append("O");
            i++;
        }
        int minutes = Integer.parseInt(dgTime[1]);
        int fiveMinutesChars = (minutes - (minutes % 5)) / 5;
        while (i < 8 + fiveMinutesChars) {
            if ((i - 7) % 3 == 0) {
                berlinCLock.append("R");
            } else {
                berlinCLock.append("Y");
            }
            i++;
        }
        while (i < 19) {
            berlinCLock.append("O");
            i++;
        }
        int oneMinutesChars = minutes % 5;
        while (i < 19 + oneMinutesChars) {
            berlinCLock.append("Y");
            i++;
        }
        while (i < 23) {
            berlinCLock.append("O");
            i++;
        }
        return berlinCLock.toString();
    }

    public String getDigitalTimeFromBerlinClock(String berlinClock) {

        /*
            handle null or empty value
        */

        if (berlinClock == null || berlinClock.length() == 0) {
            throw new NullParameterException("parameter is empty");
        }

        /*
            handle incorrect format case
        */

        if (!berlinClock.matches(VALID_BERLIN_CLOCK)) {
            throw new InvalidBerlinClockException("Berlin clock format is not correct");
        }

        /*
            Idea is to parse berlin clock string char by char and compute resulted values for  hours, minutes for digital time
        */

        String res;
        Integer hours = 0;
        Integer minutes = 0;
        for (int i = 1; i < 5; i++) {
            if (berlinClock.charAt(i) == 'R') {
                hours = hours + 5;
            }
        }
        for (int i = 5; i < 9; i++) {
            if (berlinClock.charAt(i) == 'R') {
                hours = hours + 1;
            }
        }
        for (int i = 9; i < 20; i++) {
            if (berlinClock.charAt(i) == 'Y' || berlinClock.charAt(i) == 'R') {
                minutes = minutes + 5;
            }
        }
        for (int i = 20; i < 24; i++) {
            if (berlinClock.charAt(i) == 'Y') {
                minutes = minutes + 1;
            }
        }
        if (hours < 10) {
            if (minutes < 10) {
                res = "0" + hours + ":" + "0" + minutes;
            } else {
                res = "0" + hours + ":" + minutes;
            }
        } else {
            if (minutes < 10) {
                res = hours + ":" + "0" + minutes;
            } else {
                res = hours + ":" + minutes;
            }
        }
        return res;
    }
}
