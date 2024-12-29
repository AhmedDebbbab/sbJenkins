package com.v1.sbjenkins.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class ListControllerTest  {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void getNumbers() throws Exception{
        mockMvc.perform(get("/api/numbers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", containsInAnyOrder(0, 1, 2, 3, 4, 5)));
    }
}