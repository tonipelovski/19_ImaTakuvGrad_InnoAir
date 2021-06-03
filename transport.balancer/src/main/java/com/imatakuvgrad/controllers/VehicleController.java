package com.imatakuvgrad.controllers;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.imatakuvgrad.VehicleType;
import com.imatakuvgrad.models.Image;
import com.imatakuvgrad.models.Vehicle;
import com.imatakuvgrad.services.ImageService;
import com.imatakuvgrad.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/vehicles")
class VehicleController {

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    @Autowired
    private ImageService imageService;

    @Autowired
    private VehicleService vehicleService;


    @GetMapping
    public List<Vehicle> findPage(@RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "size" , required = false) Integer size) {
        List<Vehicle> resultList;
        if (page != null && size != null) {
            resultList = vehicleService.findPaginated(page, size).stream().toList();
        } else {
            resultList = vehicleService.findAll();
        }
        return  resultList;
    }


    @GetMapping(value = "/{id}")
    public Vehicle findById(@PathVariable("id") Long id) {
        return vehicleService.findById(id);
    }

    @GetMapping(value = "/count")
    @ResponseBody
    public Long countVehicles() {
        return vehicleService.count();
    }

    @PostMapping
    @ResponseBody
    public Long create(Vehicle vehicle) {
        return vehicleService.create(vehicle).getId();
    }

}
