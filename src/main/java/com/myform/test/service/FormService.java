package com.myform.test.service;

import com.myform.test.dto.FormDtoRequest;
import com.myform.test.dto.FormDtoResponse;
import com.myform.test.exception.FormNotFoundException;
import com.myform.test.model.Form;

public interface FormService {

    FormDtoResponse create(FormDtoRequest newFormDto);
    FormDtoResponse update(Long id, FormDtoRequest updatedFormDto) throws FormNotFoundException;
    FormDtoResponse getFormById(Long id) throws FormNotFoundException;
}
