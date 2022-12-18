package com.alzohra.berlinclock.services;

import com.alzohra.berlinclock.exceptions.InvalidBerlinClockException;
import com.alzohra.berlinclock.exceptions.InvalidDigitalTimeException;
import com.alzohra.berlinclock.exceptions.NullParameterException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
   unit tests for BerlinClockService functions
   In this case, we have used @SpringBootTest to set up application context for test (very close to prod scenario)
   We have used assertThrows to check exception throw in case of error as shown in test cases at bottom.
*/

@SpringBootTest
public class BerlinClockServiceTest {
    @Autowired
    BerlinClockService berlinClockService;


    @Test
    public void getBerlinClockFromDigitalTimeWithValidParams() throws Exception {
        String res = berlinClockService.getBerlinClockFromDigitalTime("15:32:21");
        assertEquals("ORRROOOOOYYRYYROOOOOYYOO", res);
    }

    @Test
    public void getBerlinClockFromDigitalTimeWithNullParameter() throws Exception {
        assertThrows(NullParameterException.class, () -> {
            berlinClockService.getBerlinClockFromDigitalTime("");
        });
    }

    @Test
    public void getBerlinClockFromDigitalTimeWithIncorrectFormat() throws Exception {
        assertThrows(InvalidDigitalTimeException.class, () -> {
            berlinClockService.getBerlinClockFromDigitalTime("15:62:56");
        });
    }

    @Test
    public void getDigitalTimeFromBerlinClockWithValidParams() throws Exception {
        String res = berlinClockService.getDigitalTimeFromBerlinClock("ORRROOOOOYYRYYROOOOOYYOO");
        assertEquals("15:32", res);
    }

    @Test
    public void getDigitalTimeFromBerlinClockWithNullParameter() throws Exception {
        assertThrows(NullParameterException.class, () -> {
            berlinClockService.getDigitalTimeFromBerlinClock("");
        });
    }

    @Test
    public void getDigitalTimeFromBerlinClockWithIncorrectFormat() throws Exception {
        assertThrows(InvalidBerlinClockException.class, () -> {
            berlinClockService.getDigitalTimeFromBerlinClock("ORRROOOOOYYRYYROOOOOYY");
        });
    }

}
