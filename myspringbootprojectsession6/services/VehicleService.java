package com.example.myspringbootprojectsession6.services;

import com.example.myspringbootprojectsession6.dto.PageResponse;
import com.example.myspringbootprojectsession6.dto.VehicleCreateRequest;
import com.example.myspringbootprojectsession6.dto.VehicleResponse;
import com.example.myspringbootprojectsession6.entities.Vehicle;
import com.example.myspringbootprojectsession6.repositories.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public VehicleResponse createVehicle(VehicleCreateRequest request) {
        Vehicle vehicle = new Vehicle(request.getLicensePlate(), request.getColor(), request.getVehicleType());
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return new VehicleResponse(
                savedVehicle.getId(),
                savedVehicle.getLicensePlate(),
                savedVehicle.getColor(),
                savedVehicle.getType()
        );
    }

    @Transactional(readOnly = true)
    public PageResponse<VehicleResponse> getPagedVehicles(
            int page, int size, String sortBy, String direction, String keyword) {
        int safePage = Math.max(page, 0);
        int safeSize = size > 0 ? size : 10;
        Sort sort = createSort(sortBy, direction);
        Pageable pageable = PageRequest.of(safePage, safeSize, sort);
        Page<VehicleResponse> vehiclePage = vehicleRepository.findAllByKeyword(normalizeKeyword(keyword), pageable);

        return new PageResponse<>(
                vehiclePage.getContent(),
                vehiclePage.getNumber(),
                vehiclePage.getSize(),
                vehiclePage.getTotalElements(),
                vehiclePage.getTotalPages(),
                vehiclePage.isLast()
        );
    }

    private Sort createSort(String sortBy, String direction) {
        if (sortBy == null || sortBy.isBlank() || direction == null || direction.isBlank()) {
            return Sort.unsorted();
        }
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        return Sort.by(sortDirection, sortBy);
    }

    private String normalizeKeyword(String keyword) {
        return keyword == null || keyword.isBlank() ? null : keyword.trim();
    }
}
