package com.myform.test.service.impl;

import com.myform.test.dto.FormDtoRequest;
import com.myform.test.dto.FormDtoResponse;
import com.myform.test.dto.mapper.FormDtoMapper;
import com.myform.test.exception.FormNotFoundException;
import com.myform.test.model.Form;
import com.myform.test.model.Sector;
import com.myform.test.repository.FormRepository;
import com.myform.test.repository.SectorRepository;
import com.myform.test.service.FormService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final SectorRepository sectorRepository;
    private final FormDtoMapper formDtoMapper;

    @Override
    public FormDtoResponse create(FormDtoRequest newFormDto) {

        // Create new form
        Form newForm = new Form(newFormDto.getUsername(),
                newFormDto.getAgreement());

        Set<Integer> strSectors = newFormDto.getSectorsId();
        Set<Sector> sectors = new HashSet<>();

        if (strSectors == null) {
            Sector sectorManufacturing = sectorRepository.findById(1)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            sectors.add(sectorManufacturing);
        } else {
            strSectors.forEach(sector -> {
                switch (sector) {
                    case 19:
                        Sector sectorConstructionMaterials = sectorRepository.findById(19)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        sectors.add(sectorConstructionMaterials);

                        break;
                    case 18:
                        Sector sectorElectronicsOptics = sectorRepository.findById(18)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        sectors.add(sectorElectronicsOptics);


                }

            });
        }

        newForm.setSectors(sectors);
        formRepository.save(newForm);

        return formDtoMapper.toFormDtoResponse(newForm);

    }


    @Override
    public FormDtoResponse update(Long id, FormDtoRequest updatedFormDto) throws FormNotFoundException {
        Optional<Form> formData = formRepository.findById(id);
        if (formData.isPresent()) {

            Set<Integer> strSectors = updatedFormDto.getSectorsId();
            Set<Sector> sectors = new HashSet<>();

            if (strSectors == null) {
                Sector sectorManufacturing = sectorRepository.findById(1)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                sectors.add(sectorManufacturing);
            } else {
                strSectors.forEach(sector -> {
                    switch (sector) {
                        case 19:
                            Sector sectorConstructionMaterials = sectorRepository.findById(19)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            sectors.add(sectorConstructionMaterials);

                            break;
                        case 18:
                            Sector sectorElectronicsOptics = sectorRepository.findById(18)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            sectors.add(sectorElectronicsOptics);


                    }

                });
            }

            Form fromDBForm = formRepository.findById(id)
                    .orElseThrow(() -> new FormNotFoundException("No form found with Id = " + id));
            fromDBForm.setUsername(updatedFormDto.getUsername());
            fromDBForm.setAgreement(updatedFormDto.getAgreement());
            fromDBForm.setSectors(sectors);

            formRepository.save(fromDBForm);

            return formDtoMapper.toFormDtoResponse(fromDBForm);
        } else {
            throw new FormNotFoundException("Form not found with id : " + id);
        }
    }


    @Override
    public FormDtoResponse getFormById(Long id) throws FormNotFoundException {
        Form formData = formRepository.findById(id)
                .orElseThrow(() -> new FormNotFoundException("No form found with Id = " + id));
        return formDtoMapper.toFormDtoResponse(formData);
    }

}
