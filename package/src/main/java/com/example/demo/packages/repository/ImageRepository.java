package com.example.demo.packages.repository;

import com.example.demo.packages.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
