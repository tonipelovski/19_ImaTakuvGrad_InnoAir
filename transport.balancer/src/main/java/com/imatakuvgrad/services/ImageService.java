package com.imatakuvgrad.services;

import com.imatakuvgrad.models.Image;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ImageService {
    List<Image> findAll();
    Image findById(Long id);
    Long create(Image image);
}
