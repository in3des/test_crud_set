package com.myform.test.controller;

import com.myform.test.dto.SectorDto;
import com.myform.test.service.SectorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Sectors", description = "SectorRestController API v1")
@CrossOrigin(origins = "http://localhost:8086", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class SectorController {

    private final SectorService sectorService;

    @GetMapping("/sectors")
    @ApiOperation(value = "getAll", notes = "Get the list of all cities", tags = "Sectors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Authentication Data is missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Forbidden operation"),
            @ApiResponse(responseCode = "404", description = "Controller not found")}
    )
    public ResponseEntity<List<SectorDto>> getAll() {
        List<SectorDto> sectors = sectorService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(sectors);
    }

}
