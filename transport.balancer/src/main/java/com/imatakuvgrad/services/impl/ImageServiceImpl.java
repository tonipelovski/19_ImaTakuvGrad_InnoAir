package com.imatakuvgrad.services.impl;

import com.imatakuvgrad.models.Image;
import com.imatakuvgrad.repositories.ImageRepository;
import com.imatakuvgrad.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Long create(Image image) {
        return imageRepository.save(image).getId();
    }
}
