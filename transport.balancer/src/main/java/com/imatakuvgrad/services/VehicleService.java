package com.imatakuvgrad.services;

import com.imatakuvgrad.models.Vehicle;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();
    Page<Vehicle> findPaginated(int page, int pageSize);
    Vehicle findById(Long id);
    Vehicle create(Vehicle vehicle);
    Long count();
}
