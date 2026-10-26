package com.example.myspringbootprojectsession6.services;

import com.example.myspringbootprojectsession6.dto.TicketSummaryResponse;
import com.example.myspringbootprojectsession6.repositories.ParkingTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private final ParkingTicketRepository parkingTicketRepository;

    public TicketService(ParkingTicketRepository parkingTicketRepository) {
        this.parkingTicketRepository = parkingTicketRepository;
    }

    @Transactional(readOnly = true)
    public List<TicketSummaryResponse> getTodaySummaries() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime startOfNextDay = today.plusDays(1).atStartOfDay();
        return parkingTicketRepository.findTodaySummaries(startOfDay, startOfNextDay);
    }
}
