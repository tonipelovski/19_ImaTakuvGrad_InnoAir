package com.imatakuvgrad.controllers;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
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
@RequestMapping("/images")
class ImageController {

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

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
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ResponseBody
    public Long create(Image image, @RequestParam Long vehicleId) {
        Vehicle vehicle = vehicleService.findById(vehicleId);
        if (vehicle == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Provide correct Vehicle Id");
        }
        image.setVehicle(vehicle);

        Thread objDetection = new Thread(() -> objectDetection(image));
        objDetection.start();

        return imageService.create(image).getId();
    }

    private void objectDetection(Image image) {
        ByteArrayResource resource = new ByteArrayResource(image.getData());
        AnnotateImageResponse response =
                this.cloudVisionTemplate.analyzeImage(
                        resource, Feature.Type.LABEL_DETECTION);

        List<String> imageLabels =
                response
                        .getLabelAnnotationsList()
                        .stream()
                        .map(EntityAnnotation::getDescription)
                        .collect(Collectors.toList());
    }

}
