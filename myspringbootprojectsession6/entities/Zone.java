package com.example.myspringbootprojectsession6.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zones")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @Column(name = "occupied_spots", nullable = false)
    private Integer occupiedSpots;

    @OneToMany(mappedBy = "zone")
    private List<ParkingTicket> parkingTickets = new ArrayList<>();

    protected Zone() {
    }

    public Zone(String name, Integer capacity, Integer occupiedSpots) {
        this.name = name;
        this.capacity = capacity;
        this.occupiedSpots = occupiedSpots;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getOccupiedSpots() {
        return occupiedSpots;
    }

    public void setOccupiedSpots(Integer occupiedSpots) {
        this.occupiedSpots = occupiedSpots;
    }

    public List<ParkingTicket> getParkingTickets() {
        return parkingTickets;
    }
}
