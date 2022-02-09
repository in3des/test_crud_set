package com.myform.test.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myform.test.dto.FormDtoResponse;
import com.myform.test.dto.mapper.FormDtoMapper;
import com.myform.test.exception.FormNotFoundException;
import com.myform.test.model.Form;
import com.myform.test.model.Sector;
import com.myform.test.repository.FormRepository;
import com.myform.test.repository.SectorRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FormServiceImplTest {

    @Mock
    private FormRepository formRepository;

    @Mock
    private SectorRepository sectorRepository;

    @Mock
    private FormDtoMapper mapper;

    @InjectMocks
    private FormServiceImpl service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new FormServiceImpl(formRepository, sectorRepository, mapper);

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

    private static final String URL = "/api/v1";

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void getFormById() throws FormNotFoundException {
        Long formId = 1L;
        Sector sector = new Sector()
                .setName("TestSector")
                .setSectors(new ArrayList<>())
                .setParentSectorName(null);
        sectorRepository.save(sector);
        Form form = new Form()
                .setId(formId)
                .setUsername("TestUsername")
                .setAgreement(true)
                .setSectors(Collections.singleton(sector));
        when(formRepository.findById(formId)).thenReturn(Optional.of(form));
        FormDtoResponse actualForm = service.getFormById(formId);
        verify(formRepository, times(1)).findById(formId);
        verifyNoMoreInteractions(formRepository);
        assertThat(actualForm).isEqualTo(form);
        assertThat(actualForm).isNotNull();
    }
}