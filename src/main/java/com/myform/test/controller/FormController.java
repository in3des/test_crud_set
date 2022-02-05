package com.myform.test.controller;

import com.myform.test.dto.FormDtoRequest;
import com.myform.test.dto.FormDtoResponse;
import com.myform.test.dto.MessageResponse;
import com.myform.test.exception.FormNotFoundException;
import com.myform.test.model.Form;
import com.myform.test.model.Sector;
import com.myform.test.repository.FormRepository;
import com.myform.test.repository.SectorRepository;
//import com.myform.test.service.FormService;
import com.myform.test.service.FormService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Tag(name = "Forms", description = "FormRestController API v1")
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class FormController {

    private final FormService formService;
    private final FormRepository formRepository;
    private final SectorRepository sectorRepository;

    @PostMapping("/form")
    public ResponseEntity<FormDtoResponse> create(FormDtoRequest formDto) {
        FormDtoResponse newFormDto = formService.create(formDto);
        return ResponseEntity.status(HttpStatus.OK).body(newFormDto);
    }

//    @PostMapping("/formcheck")
//    public String create22(FormDtoRequest formDto) {
//        return "bad happens";
//    }

//    @PostMapping("/formcheck")
//    public String create22(Form form) {
//        return form.toString();
//    }

    @PostMapping("/formcheck")
    public ResponseEntity<?> create22(FormDtoRequest newFormDto) {
//        Form newForm = formRepository.save(formDtoMapper.toFormEntity(newFormDto));

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
                    case 1:
                        Sector sectorConstructionMaterials = sectorRepository.findById(19)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        sectors.add(sectorConstructionMaterials);

                        break;
                    case 19:
                        Sector sectorElectronicsOptics = sectorRepository.findById(18)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        sectors.add(sectorElectronicsOptics);
                }
            });
        }

        newForm.setSectors(sectors);
        formRepository.save(newForm);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

    @PutMapping("/form/{id}")
    public ResponseEntity<FormDtoResponse> update(@PathVariable Long id, FormDtoRequest formDto) throws FormNotFoundException {
        FormDtoResponse newFormDto = formService.update(id, formDto);
        return ResponseEntity.status(HttpStatus.OK).body(newFormDto);
    }

    @GetMapping("/form/{id}")
    public ResponseEntity<FormDtoResponse> getFormById(@PathVariable Long id) throws FormNotFoundException {
        FormDtoResponse newFormDto = formService.getFormById(id);
        return ResponseEntity.status(HttpStatus.OK).body(newFormDto);
    }

}
