package com.example.myspringbootprojectsession6.controllers;

import com.example.myspringbootprojectsession6.dto.ApiResponse;
import com.example.myspringbootprojectsession6.dto.TicketRequest;
import com.example.myspringbootprojectsession6.dto.TicketResponse;
import com.example.myspringbootprojectsession6.dto.TicketSummaryResponse;
import com.example.myspringbootprojectsession6.services.ParkingService;
import com.example.myspringbootprojectsession6.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class ParkingTicketController {

    private final ParkingService parkingService;
    private final TicketService ticketService;

    public ParkingTicketController(ParkingService parkingService, TicketService ticketService) {
        this.parkingService = parkingService;
        this.ticketService = ticketService;
    }

    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<TicketResponse>> checkIn(
            @RequestBody TicketRequest request) {
        TicketResponse response = parkingService.checkIn(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Vehicle checked in successfully", response));
    }

    @PutMapping("/check-out/{vehicleId}")
    public ResponseEntity<ApiResponse<TicketResponse>> checkOut(
            @PathVariable Long vehicleId) {
        TicketResponse response = parkingService.checkOut(vehicleId);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Vehicle checked out successfully", response));
    }

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<List<TicketSummaryResponse>>> getTodaySummary() {
        List<TicketSummaryResponse> response = ticketService.getTodaySummaries();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Today's ticket summaries retrieved successfully", response));
    }
}
