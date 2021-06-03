package com.imatakuvgrad.repositories;

import com.imatakuvgrad.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
