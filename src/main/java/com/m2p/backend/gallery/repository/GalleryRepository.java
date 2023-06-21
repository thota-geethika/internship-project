package com.m2p.backend.gallery.repository;


import com.m2p.backend.gallery.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<ImageModel,Long> {

    @Query(value = "SELECT * FROM image_table",nativeQuery = true)
    public ImageModel[] getImages();



}
