package com.myform.test.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
//@Getter
//@Setter
public class FormDtoRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    private Boolean agreement;

    private Set<Integer> sectorsId;

}
