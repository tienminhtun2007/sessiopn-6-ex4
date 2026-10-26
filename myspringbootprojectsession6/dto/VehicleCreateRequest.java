package com.example.myspringbootprojectsession6.dto;

import com.example.myspringbootprojectsession6.entities.VehicleType;

public class VehicleCreateRequest {

    private String licensePlate;
    private String color;
    private VehicleType vehicleType;

    public VehicleCreateRequest() {
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
