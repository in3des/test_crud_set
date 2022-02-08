package com.myform.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myform.test.service.FormService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

class FormControllerTest {

    @Mock
    private FormService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FormController appController;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL = "/api/v1";

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(appController).build();
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void getFormById() {
    }
}