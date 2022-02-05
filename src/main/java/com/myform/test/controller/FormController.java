package com.myform.test.controller;

import com.myform.test.dto.FormDtoRequest;
import com.myform.test.dto.FormDtoResponse;
import com.myform.test.exception.FormNotFoundException;
import com.myform.test.service.FormService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Forms", description = "FormRestController API v1")
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class FormController {

    private final FormService formService;

    @PostMapping("/form")
    public ResponseEntity<FormDtoResponse> create(@RequestBody FormDtoRequest formDto) {
        FormDtoResponse newFormDto = formService.create(formDto);
        return ResponseEntity.status(HttpStatus.OK).body(newFormDto);
    }

    @PutMapping("/form/{id}")
    public ResponseEntity<FormDtoResponse> update(@PathVariable Long id, @RequestBody FormDtoRequest formDto) throws FormNotFoundException {
        FormDtoResponse newFormDto = formService.update(id, formDto);
        return ResponseEntity.status(HttpStatus.OK).body(newFormDto);
    }

    @GetMapping("/form/{id}")
    public ResponseEntity<FormDtoResponse> getFormById(@PathVariable Long id) throws FormNotFoundException {
        FormDtoResponse newFormDto = formService.getFormById(id);
        return ResponseEntity.status(HttpStatus.OK).body(newFormDto);
    }

}
