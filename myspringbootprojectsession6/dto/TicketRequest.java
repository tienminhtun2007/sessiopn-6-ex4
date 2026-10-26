package com.example.myspringbootprojectsession6.dto;

public class TicketRequest {

    private Long vehicleId;
    private Long zoneId;

    public TicketRequest() {
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }
}
