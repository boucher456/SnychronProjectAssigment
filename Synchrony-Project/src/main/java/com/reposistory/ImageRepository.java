package com.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
