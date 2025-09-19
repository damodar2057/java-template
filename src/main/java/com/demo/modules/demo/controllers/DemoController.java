package com.demo.modules.demo.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.demo.modules.demo.dtos.CreateDemoDto;
import com.demo.modules.demo.dtos.UpdateDemoDto;
import com.demo.modules.demo.entities.DemoEntity;
import com.demo.modules.demo.services.DemoService;
import com.demo.shared.dto.PaginatedResponse;
import com.demo.shared.dto.QueryDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/demo")
@Tag(name = "Demo Controller", description = "APIs for managing demo records")
public class DemoController {
    private final DemoService demoService;
    private static final Logger logger = LogManager.getLogger(DemoController.class);

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping
    @Operation(summary = "List all demo records with pagination and sorting", responses = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval", content = @Content(schema = @Schema(implementation = PaginatedResponse.class)))
    })
    public PaginatedResponse<DemoEntity> getAllPaginated(
            @Parameter(description = "Pagination and sorting query params") @ModelAttribute QueryDto query) {
        Page<DemoEntity> pageResult = demoService.findAllWithPagination(query);
        return PaginatedResponse.fromPage(pageResult);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Fetch a demo record by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Record found", content = @Content(schema = @Schema(implementation = DemoEntity.class))),
            @ApiResponse(responseCode = "404", description = "Record not found")
    })
    public DemoEntity getById(
            @Parameter(description = "ID of the demo record", example = "1") @PathVariable Long id) {
        return demoService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new demo record", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data for creating a demo record", required = true, content = @Content(schema = @Schema(implementation = CreateDemoDto.class))), responses = {
            @ApiResponse(responseCode = "201", description = "Record created successfully", content = @Content(schema = @Schema(implementation = DemoEntity.class)))
    })
    public DemoEntity create(@Valid @RequestBody CreateDemoDto dto) {
        return demoService.save(dto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a demo record by ID", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data for updating a demo record (only non-null fields are updated)", required = true, content = @Content(schema = @Schema(implementation = UpdateDemoDto.class))), responses = {
            @ApiResponse(responseCode = "200", description = "Record updated successfully", content = @Content(schema = @Schema(implementation = DemoEntity.class))),
            @ApiResponse(responseCode = "404", description = "Record not found")
    })
    public DemoEntity update(
            @Valid @RequestBody UpdateDemoDto dto,
            @Parameter(description = "ID of the demo record to update", example = "1") @PathVariable Long id) {
        return demoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a demo record by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Record deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Record not found")
    })
    public void remove(
            @Parameter(description = "ID of the demo record to delete", example = "1") @PathVariable Long id) {
        demoService.delete(id);
    }
}
