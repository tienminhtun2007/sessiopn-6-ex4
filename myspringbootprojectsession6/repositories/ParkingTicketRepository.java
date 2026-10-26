package com.example.myspringbootprojectsession6.repositories;

import com.example.myspringbootprojectsession6.dto.TicketSummaryResponse;
import com.example.myspringbootprojectsession6.entities.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {

    Optional<ParkingTicket> findFirstByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Long vehicleId);

    @Query("""
            select new com.example.myspringbootprojectsession6.dto.TicketSummaryResponse(
                t.id, t.vehicle.licensePlate, t.zone.name, t.checkInTime, t.checkOutTime
            )
            from ParkingTicket t
            where t.checkInTime >= :startOfDay
              and t.checkInTime < :startOfNextDay
            order by t.checkInTime desc
            """)
    List<TicketSummaryResponse> findTodaySummaries(
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("startOfNextDay") LocalDateTime startOfNextDay);
}
