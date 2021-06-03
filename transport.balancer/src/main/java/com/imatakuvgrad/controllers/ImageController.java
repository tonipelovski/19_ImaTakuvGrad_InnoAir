package com.imatakuvgrad.controllers;

import com.imatakuvgrad.models.Image;
import com.imatakuvgrad.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping
    public List<Image> findAll() {
        return imageService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Image findById(@PathVariable("id") Long id) {
        return imageService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Image image) {
        return imageService.create(image);
    }

}
