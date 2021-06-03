package com.imatakuvgrad.controllers;

import com.google.cloud.vision.v1.*;
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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

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
    @ResponseBody
    public HttpStatus create(Image image, @RequestParam Long vehicleId) {
        Vehicle vehicle = vehicleService.findById(vehicleId);
        if (vehicle == null || image.getData() == null || image.getData().isEmpty()) {
            return HttpStatus.BAD_REQUEST;
        }
        image.setVehicle(vehicle);
        image.setPeopleCount(0);

        Thread objDetection = new Thread(() -> objectDetection(image));
        objDetection.start();

        imageService.create(image);
        return HttpStatus.ACCEPTED;
    }

    private void objectDetection(Image image) {
        byte[] bytes = Base64.getDecoder().decode(image.getData());
        ByteArrayResource resource = new ByteArrayResource(bytes);
        AnnotateImageResponse response =
                this.cloudVisionTemplate.analyzeImage(
                        resource, Feature.Type.FACE_DETECTION);
        List<FaceAnnotation> faceAnnotationsList = response.getFaceAnnotationsList();
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
            if (bufferedImage != null && !faceAnnotationsList.isEmpty()) {
                for (FaceAnnotation faceAnnotation : faceAnnotationsList) {
                    annotateWithFace(bufferedImage, faceAnnotation);
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
                String base64Encoded = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
                image.setData(base64Encoded);
                image.setPeopleCount(faceAnnotationsList.size());
            }
            imageService.update(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void annotateWithFace(BufferedImage img, FaceAnnotation face) {
        final int BORDER_COLOR = 0x00ff00;
        Graphics2D gfx = img.createGraphics();
        Polygon poly = new Polygon();
        for (Vertex vertex : face.getFdBoundingPoly().getVerticesList()) {
            poly.addPoint(vertex.getX(), vertex.getY());
        }
        gfx.setStroke(new BasicStroke(5));
        gfx.setColor(new Color(BORDER_COLOR));
        gfx.draw(poly);
    }
}
