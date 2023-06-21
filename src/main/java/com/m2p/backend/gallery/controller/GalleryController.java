package com.m2p.backend.gallery.controller;


import com.m2p.backend.gallery.model.ImageModel;
import com.m2p.backend.gallery.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @PostMapping("/saveimage")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveImage(@RequestBody ImageModel image)
    {
        galleryService.saveImage(image);
    }

    @GetMapping("/fetchimages")
    @ResponseStatus(HttpStatus.OK)
    public ImageModel[] fetchImages()
    {
      return  galleryService.fetchImages();
    }
}
