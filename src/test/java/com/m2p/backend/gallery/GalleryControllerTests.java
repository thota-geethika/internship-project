package com.m2p.backend.gallery;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2p.backend.gallery.controller.GalleryController;
import com.m2p.backend.gallery.model.ImageModel;
import com.m2p.backend.gallery.service.GalleryService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(GalleryService.class)
@WebMvcTest(GalleryController.class)
public class GalleryControllerTests
{
    @Nested
    class ImagesController{

        @MockBean
        private GalleryService galleryService;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void toCheckIfImageCanBeStoredInDatabase () throws Exception
        {
            ImageModel imageModel = new ImageModel(1,"https://tinyurl.com/ued7cez6","normal",1);
            String json = objectMapper.writer().writeValueAsString(imageModel);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/saveimage")
                    .contentType(MediaType.APPLICATION_JSON).content(json))
                    .andExpect(status().isCreated());


        }

        @Test
        public void toCheckIfImagesAreRetrievedFromDataBase() throws Exception
        {
            ImageModel[] images = new ImageModel[2];
            ImageModel imageOne = new ImageModel(1,"https://tinyurl.com/ued7cez6","normal",1);
            images[0]=imageOne;
            images[1]=imageOne;




            Mockito.when(galleryService.fetchImages()).thenReturn(images);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/fetchimages"))
                    .andExpect(status().isOk())
                    .andDo(print());


        }
    }
}
