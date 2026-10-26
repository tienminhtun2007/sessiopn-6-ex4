package com.example.myspringbootprojectsession6.dto;

import com.example.myspringbootprojectsession6.entities.VehicleType;

public class VehicleResponse {

    private Long id;
    private String licensePlate;
    private String color;
    private VehicleType vehicleType;

    public VehicleResponse(Long id, String licensePlate, String color, VehicleType vehicleType) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.color = color;
        this.vehicleType = vehicleType;
    }

    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getColor() {
        return color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
