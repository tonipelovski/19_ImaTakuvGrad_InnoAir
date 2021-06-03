package com.imatakuvgrad.services;

import com.imatakuvgrad.models.Image;
import com.imatakuvgrad.models.Vehicle;
import org.springframework.stereotype.Service;
import java.util.List;

public interface ImageService {
    List<Image> findAll();
    List<Image> findByVehicleOrderedByCreatedAt(Vehicle vehicle);
    Image findById(Long id);
    Image create(Image image);
    Image update(Image image);

}
