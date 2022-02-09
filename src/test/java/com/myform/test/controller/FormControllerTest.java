package com.myform.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myform.test.dto.FormDtoRequest;
import com.myform.test.model.Form;
import com.myform.test.model.Sector;
import com.myform.test.repository.FormRepository;
import com.myform.test.repository.SectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FormControllerTest {

    @Mock
    private FormRepository formRepository;

    @Mock
    private SectorRepository sectorRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL = "/api/v1";

    @BeforeEach
    public void init() {
        Sector sector = new Sector()
                .setName("TestSector")
                .setSectors(new ArrayList<>())
                .setParentSectorName(null);
        sectorRepository.save(sector);

        formRepository.save(new Form()
                .setUsername("TestUsername")
                .setAgreement(true)
                .setSectors(Collections.singleton(sector))
        );
    }

    @Test
    public void create() throws Exception {
        FormDtoRequest request = new FormDtoRequest()
                .setUsername("TestUsername")
                .setAgreement(true)
                .setSectorsId(Collections.singleton(1));

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post(URL + "/form")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request));

        ResultActions perform = mockMvc.perform(requestBuilder);

        perform.andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        FormDtoRequest request = new FormDtoRequest()
                .setUsername("TestUsername")
                .setAgreement(true)
                .setSectorsId(Collections.singleton(1));

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .put(URL + "/form/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request));

        ResultActions perform = mockMvc.perform(requestBuilder);

        perform.andExpect(status().isOk());
    }

    @Test
    void getFormByIdSuccess() throws Exception {
        mockMvc.perform(get(URL + "/form/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1));
    }

    @Test
    public void getFormByIdFailed() throws Exception {
        mockMvc.perform(get(URL + "/form/1001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


}