package com.myform.test.dto;

import lombok.Data;

import java.util.List;

@Data
public class SectorDto {
    private Integer id;
    private String name;
    private Integer parentId;
    private List<SectorDto> sectors;
}
