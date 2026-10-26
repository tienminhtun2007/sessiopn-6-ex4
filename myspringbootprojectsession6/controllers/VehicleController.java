package com.example.myspringbootprojectsession6.controllers;

import com.example.myspringbootprojectsession6.dto.ApiResponse;
import com.example.myspringbootprojectsession6.dto.PageResponse;
import com.example.myspringbootprojectsession6.dto.VehicleCreateRequest;
import com.example.myspringbootprojectsession6.dto.VehicleResponse;
import com.example.myspringbootprojectsession6.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponse>> createVehicle(
            @RequestBody VehicleCreateRequest request) {
        VehicleResponse response = vehicleService.createVehicle(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Vehicle created successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<VehicleResponse>>> getVehicles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String keyword) {
        PageResponse<VehicleResponse> response =
                vehicleService.getPagedVehicles(page, size, sortBy, direction, keyword);
        return ResponseEntity.ok(new ApiResponse<>(true, "Vehicles retrieved successfully", response));
    }
}
