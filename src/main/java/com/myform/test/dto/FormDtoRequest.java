package com.myform.test.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class FormDtoRequest {

    @NotEmpty(message = "Name field should not be blank")
    @Size(min = 5, max = 30, message = "Please correct name size")
    private String username;

    @NotNull
    private Boolean agreement;

    @NotNull
    private Set<Integer> sectorsId;

}
