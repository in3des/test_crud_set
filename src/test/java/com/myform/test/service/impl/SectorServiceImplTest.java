package com.myform.test.service.impl;

import com.myform.test.dto.SectorDto;
import com.myform.test.dto.mapper.SectorDtoMapper;
import com.myform.test.model.Sector;
import com.myform.test.repository.SectorRepository;
import com.myform.test.service.SectorService;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@NoArgsConstructor
class SectorServiceImplTest {

    @Mock
    private SectorRepository repo;

    @Mock
    private SectorDtoMapper mapper;

    @InjectMocks
    private SectorServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new SectorServiceImpl(repo, mapper);
    }

    @Test
    void should_find_all_sectors() {
        when(repo.findAll()).thenReturn(loadData());
        List<SectorDto> list = service.getAll();
        assertThat(list.size()).isEqualTo(4);
        assertThat(list).isNotNull();
    }

    List<Sector> loadData(){

        List<Sector> list = new ArrayList<>();
        list.add(new Sector()
                .setName("TestSector1")
                .setSectors(new ArrayList<>())
                .setParentSectorName(null));
        list.add(new Sector()
                .setName("TestSector2")
                .setSectors(new ArrayList<>())
                .setParentSectorName(null));
        list.add(new Sector()
                .setName("TestSector3")
                .setSectors(new ArrayList<>())
                .setParentSectorName(null));
        list.add(new Sector()
                .setName("TestSector4")
                .setSectors(new ArrayList<>())
                .setParentSectorName(null));

        return list;
    }
}