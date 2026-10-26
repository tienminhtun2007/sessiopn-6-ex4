package com.example.myspringbootprojectsession6.repositories;

import com.example.myspringbootprojectsession6.dto.VehicleResponse;
import com.example.myspringbootprojectsession6.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("""
            select new com.example.myspringbootprojectsession6.dto.VehicleResponse(
                v.id, v.licensePlate, v.color, v.type
            )
            from Vehicle v
            where :keyword is null
               or lower(v.licensePlate) like lower(concat('%', :keyword, '%'))
            """)
    Page<VehicleResponse> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
