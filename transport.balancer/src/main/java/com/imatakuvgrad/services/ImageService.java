package com.imatakuvgrad.services;

import com.imatakuvgrad.models.Image;
import org.springframework.stereotype.Service;
import java.util.List;

public interface ImageService {
    List<Image> findAll();
    Image findById(Long id);
    Image create(Image image);
}
