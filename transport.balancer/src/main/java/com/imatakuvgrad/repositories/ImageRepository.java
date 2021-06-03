package com.imatakuvgrad.repositories;

import com.imatakuvgrad.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {}
