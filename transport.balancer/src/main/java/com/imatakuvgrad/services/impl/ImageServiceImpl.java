package com.imatakuvgrad.services.impl;

import com.imatakuvgrad.models.Image;
import com.imatakuvgrad.models.Vehicle;
import com.imatakuvgrad.repositories.ImageRepository;
import com.imatakuvgrad.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public List<Image> findAll() {
        return new ArrayList<>(imageRepository.findAll());
    }

    @Override
    public Image findById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public Image create(Image image) {
        return imageRepository.saveAndFlush(image);
    }

    @Override
    public Image update(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public List<Image> findByVehicleOrderedByCreatedAt(Vehicle vehicle) {
        return imageRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }
}
