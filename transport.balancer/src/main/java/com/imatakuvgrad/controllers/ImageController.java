package com.imatakuvgrad.controllers;

import com.imatakuvgrad.models.Image;
import com.imatakuvgrad.models.Vehicle;
import com.imatakuvgrad.services.ImageService;
import com.imatakuvgrad.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/images")
class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private VehicleService vehicleService;


    @GetMapping
    public List<Image> findAll() {
        return imageService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Image findById(@PathVariable("id") Long id) {
        return imageService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public Long create(Image image, @RequestParam Long vehicleId) {
        Vehicle vehicle = vehicleService.findById(vehicleId);
        if (vehicle == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Provide correct Vehicle Id");
        }
        image.setVehicle(vehicle);
        return imageService.create(image).getId();
    }

}
