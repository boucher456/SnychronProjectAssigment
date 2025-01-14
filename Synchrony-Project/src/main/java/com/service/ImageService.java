package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Image;
import com.reposistory.ImageRepository;

@Service
public class ImageService {
	@Autowired
	private ImageRepository imageRepository;

	public Image saveImage(Image image) {
		return imageRepository.save(image);
	}

	public void deleteImage(Long id) {
		imageRepository.deleteById(id);
	}
}