package com.imatakuvgrad.services;

import com.imatakuvgrad.models.Vehicle;
import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();
    Vehicle findById(Long id);
    Vehicle create(Vehicle vehicle);
}
