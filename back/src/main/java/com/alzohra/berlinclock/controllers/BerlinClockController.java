package com.alzohra.berlinclock.controllers;

import com.alzohra.berlinclock.services.BerlinClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
    This class contains our main controllers, to which we inject the BerlinClock service.
    Two main rest api are defined.
    Both are with Get since, we do not need complicated data from front end to make conversion, all we need is a string.
*/
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class BerlinClockController {

    @Autowired
    BerlinClockService berlinClockService;

    @GetMapping("/getBerlinClock")
    ResponseEntity<String> getBerlinClockFromDigitalTime(@RequestParam("digitalTime") String digitalTime) {
        return new ResponseEntity<>(
                berlinClockService.
                        getBerlinClockFromDigitalTime(digitalTime),
                HttpStatus.OK);
    }

    @GetMapping("/getDigitalTime")
    ResponseEntity<String> getDigitalTimeFromBerlinClock(@RequestParam("berlinClock") String berlinClock) {
        return new ResponseEntity<>(
                berlinClockService.getDigitalTimeFromBerlinClock(berlinClock),
                HttpStatus.OK);
    }

}
