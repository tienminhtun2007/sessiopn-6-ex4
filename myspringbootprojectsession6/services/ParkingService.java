package com.example.myspringbootprojectsession6.services;

import com.example.myspringbootprojectsession6.dto.TicketRequest;
import com.example.myspringbootprojectsession6.dto.TicketResponse;
import com.example.myspringbootprojectsession6.entities.ParkingTicket;
import com.example.myspringbootprojectsession6.entities.Vehicle;
import com.example.myspringbootprojectsession6.entities.Zone;
import com.example.myspringbootprojectsession6.repositories.ParkingTicketRepository;
import com.example.myspringbootprojectsession6.repositories.VehicleRepository;
import com.example.myspringbootprojectsession6.repositories.ZoneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class ParkingService {

    private final VehicleRepository vehicleRepository;
    private final ZoneRepository zoneRepository;
    private final ParkingTicketRepository parkingTicketRepository;

    public ParkingService(VehicleRepository vehicleRepository,
                          ZoneRepository zoneRepository,
                          ParkingTicketRepository parkingTicketRepository) {
        this.vehicleRepository = vehicleRepository;
        this.zoneRepository = zoneRepository;
        this.parkingTicketRepository = parkingTicketRepository;
    }

    @Transactional
    public TicketResponse checkIn(TicketRequest request) {
        Vehicle vehicle = vehicleRepository.findById(requiredId(request.getVehicleId(), "vehicleId"))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vehicle not found"));
        Zone zone = zoneRepository.findById(requiredId(request.getZoneId(), "zoneId"))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Zone not found"));

        if (parkingTicketRepository
                .findFirstByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(vehicle.getId())
                .isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Vehicle is already checked in");
        }

        if (zone.getOccupiedSpots() >= zone.getCapacity()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Zone is full");
        }

        zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);
        ParkingTicket ticket = new ParkingTicket(LocalDateTime.now(), vehicle, zone);
        ParkingTicket savedTicket = parkingTicketRepository.save(ticket);
        return toResponse(savedTicket);
    }

    @Transactional
    public TicketResponse checkOut(Long vehicleId) {
        Long requiredVehicleId = requiredId(vehicleId, "vehicleId");
        ParkingTicket ticket = parkingTicketRepository
                .findFirstByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(requiredVehicleId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No active parking ticket found for vehicle"));

        ticket.setCheckOutTime(LocalDateTime.now());
        Zone zone = ticket.getZone();
        zone.setOccupiedSpots(Math.max(0, zone.getOccupiedSpots() - 1));
        return toResponse(parkingTicketRepository.save(ticket));
    }

    private TicketResponse toResponse(ParkingTicket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getVehicle().getLicensePlate(),
                ticket.getZone().getName(),
                ticket.getCheckInTime(),
                ticket.getCheckOutTime()
        );
    }

    private Long requiredId(Long id, String fieldName) {
        if (id == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, fieldName + " is required");
        }
        return id;
    }
}
