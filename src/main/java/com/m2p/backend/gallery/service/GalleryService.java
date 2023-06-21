package com.m2p.backend.gallery.service;

import com.m2p.backend.gallery.model.ImageModel;
import com.m2p.backend.gallery.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GalleryService {


    @Autowired
    private GalleryRepository galleryRepository;
    public void saveImage(ImageModel image)
    {
        // save , this is used for upload
        galleryRepository.save(image);
    }

    public ImageModel[] fetchImages()
    {
        ImageModel images[];
        images = galleryRepository.getImages();
        return images;
    }
}
