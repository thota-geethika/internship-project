package com.m2p.backend.gallery;

import com.m2p.backend.gallery.model.ImageModel;
import com.m2p.backend.gallery.repository.GalleryRepository;
import com.m2p.backend.gallery.service.GalleryService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GalleryServiceTests {

    @InjectMocks
    private GalleryService galleryService;

    @Mock
    private GalleryRepository galleryRepository;

    @Test
    void toCheckIfImagesAreRetrieved(){
        ImageModel[] images = new ImageModel[2];
        ImageModel imageOne = new ImageModel(1,"https://tinyurl.com/ued7cez6","normal",1);
        images[0]=imageOne;
        images[1]=imageOne;

        Mockito.when(galleryRepository.getImages()).thenReturn(images);
        ImageModel imagesList[] = galleryService.fetchImages();
        verify(galleryRepository).getImages();
    }

}
