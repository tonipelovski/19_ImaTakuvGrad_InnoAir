package com.imatakuvgrad.services.impl;

import com.imatakuvgrad.models.Vehicle;
import com.imatakuvgrad.repositories.VehicleRepository;
import com.imatakuvgrad.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAll() {
        return new ArrayList<>(vehicleRepository.findAll());
    }

    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Vehicle> findPaginated(int page, int pageSize) {
        return vehicleRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.saveAndFlush(vehicle);
    }
}
