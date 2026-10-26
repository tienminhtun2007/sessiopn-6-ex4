package com.example.myspringbootprojectsession6.dto;

import java.time.LocalDateTime;

public class TicketResponse {

    private Long id;
    private String licensePlate;
    private String zoneName;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    public TicketResponse(Long id, String licensePlate, String zoneName,
                          LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.zoneName = zoneName;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getZoneName() {
        return zoneName;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }
}
