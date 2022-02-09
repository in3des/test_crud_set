package com.myform.test.service.impl;

import com.myform.test.dto.SectorDto;
import com.myform.test.dto.mapper.SectorDtoMapper;
import com.myform.test.model.Sector;
import com.myform.test.repository.SectorRepository;
import com.myform.test.service.SectorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
@AllArgsConstructor
public class SectorServiceImpl implements SectorService {

    private final SectorRepository sectorRepository;
    private final SectorDtoMapper sectorDtoMapper;

    @Override
    public List<SectorDto> getAll() {
//        List<Sector> sectors = sectorRepository.findAll();
        List<Sector> sectors = sectorRepository.findAllByParentSectorNameNull();
        return sectorDtoMapper.toSectorDtoCollection(sectors);
    }
}
