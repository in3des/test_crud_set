package com.myform.test.dto;

import com.myform.test.model.Sector;
import lombok.Data;

import java.util.Set;

@Data
public class FormDtoResponse {
    private Long id;
    private String username;
    private Boolean agreement;
    private Set<Sector> sectors;

}
