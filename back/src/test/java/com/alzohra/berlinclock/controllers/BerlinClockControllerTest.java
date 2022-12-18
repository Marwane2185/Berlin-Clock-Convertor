package com.alzohra.berlinclock.controllers;

import com.alzohra.berlinclock.services.BerlinClockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
   unit tests for BerlinClockController APIs
   We need to use WebMvcTest to focus only on spring mvc components (we have to cite classes involved as shown in line 21)
*/

@WebMvcTest({BerlinClockController.class, BerlinClockService.class})
public class BerlinClockControllerTest {

    /*
        We use MockMvc to mimic Http request and check server response for each case
    */
    @Autowired
    private MockMvc mvc;

    @Test
    public void getBerlinClockFromDigitalTimeWithValidParams() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/getBerlinClock?digitalTime=15:32:21")
                        .content("")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("ORRROOOOOYYRYYROOOOOYYOO", result.getResponse().getContentAsString());
    }

    @Test
    public void getBerlinClockFromDigitalTimeWithNullParameter() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/getBerlinClock?digitalTime=")
                        .content("")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
        assertEquals("{\"statusCode\":400,\"message\":\"parameter is empty\"}", result.getResponse().getContentAsString());
    }

    @Test
    public void getBerlinClockFromDigitalTimeWithIncorrectFormat() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/getBerlinClock?digitalTime=15:62:56")
                        .content("")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
        assertEquals("{\"statusCode\":400,\"message\":\"Digital time format is not correct\"}", result.getResponse().getContentAsString());
    }

    @Test
    public void getDigitalTimeFromBerlinClockWithValidParams() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/getDigitalTime?berlinClock=ORRROOOOOYYRYYROOOOOYYOO")
                        .content("")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("15:32", result.getResponse().getContentAsString());
    }

    @Test
    public void getDigitalTimeFromBerlinClockWithNullParameter() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/getDigitalTime?berlinClock=")
                        .content("")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
        assertEquals("{\"statusCode\":400,\"message\":\"parameter is empty\"}", result.getResponse().getContentAsString());
    }

    @Test
    public void getDigitalTimeFromBerlinClockWithIncorrectFormat() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/getDigitalTime?berlinClock=ORRROOOOOYYRYYROOOOO")
                        .content("")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
        assertEquals("{\"statusCode\":400,\"message\":\"Berlin clock format is not correct\"}", result.getResponse().getContentAsString());
    }

}
